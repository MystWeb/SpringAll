package com.proaimltd.kafka.producer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.kafka.support.ProducerListener;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;

/**
 * @author: ziming.xing
 * @date: 2019/8/26 20:55
 */
@Slf4j
@Component
public class KafkaSendResultHandler implements ProducerListener {

    @Override
    public void onSuccess(ProducerRecord producerRecord, RecordMetadata recordMetadata) {
        log.info(MessageFormat.format("推送数据成功 : {0} ，时间戳 : {1}", producerRecord.toString(), producerRecord.timestamp()));
    }

    @Override
    public void onError(ProducerRecord producerRecord, Exception exception) {
        log.error(MessageFormat.format("推送数据出错，topic:{0},data:{1}"
                , producerRecord.topic(), producerRecord.toString()));
    }
}
