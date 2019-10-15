package com.proaimltd.kafka.controller;

import com.alibaba.fastjson.JSON;
import com.proaimltd.kafka.constants.KafkaConstants;
import com.proaimltd.kafka.model.Programmer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

import static com.proaimltd.kafka.constants.KafkaConstants.TOPIC_BEAN;
import static com.proaimltd.kafka.constants.KafkaConstants.TOPIC_GROUP;


/**
 * @author: ziming.xing
 * @date: 2019/8/26 20:59
 */
@Slf4j
@RestController
public class KafkaSendMsgController {
    @Resource
    private KafkaTemplate<String, Object> kafkaTemplate;

    /***
     * 发送消息体为基本类型的消息
     */
    @GetMapping("/sendSimple")
    public void sendSimple() {
        //发送带有时间戳的消息
        kafkaTemplate.send(KafkaConstants.TOPIC_INTERNAL, 0, System.currentTimeMillis(), "0", "send message with timestamp");
    }

    /***
     * 多消费者组、组中多消费者对同一主题的消费情况
     */
    @GetMapping("/sendGroup")
    public void sendGroup() {
        for (int i = 0; i < 4; i++) {
            try {
                kafkaTemplate.send(TOPIC_GROUP, i % 4, "key", "hello group：：：： " + i);
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 第二个参数指定分区，第三个参数指定消息键 分区优先
            /*ListenableFuture<SendResult<String, Object>> future = kafkaTemplate.send(TOPIC_GROUP, i % 4, "key", "hello group：：：： " + i);
            future.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
                @Override
                public void onFailure(Throwable throwable) {
                    log.info("发送消息失败:" + throwable.getMessage());
                }

                @Override
                public void onSuccess(SendResult<String, Object> sendResult) {
                    log.info("发送结果:" + sendResult.toString());
                }
            });*/
        }
    }

    /***
     * 发送消息体为 bean 的消息
     */
    @GetMapping("/sendBean")
    public void sendBean() {
        Programmer programmer = new Programmer("小明", 12, 21212.33f, new Date());
        kafkaTemplate.send(TOPIC_BEAN, JSON.toJSON(programmer).toString());
    }
}
