package com.proaim.redis.pubsub.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.proaim.redis.pubsub.model.entity.dto.MessageDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @author: ziming.xing
 * @date: 2019/7/19 14:44
 */
@Slf4j
@Service
public class ProMessageListener implements MessageListener {
    /*@Autowired
    private ObjectMapper objectMapper;*/

    private final ObjectMapper objectMapper;

    public ProMessageListener(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void onMessage(Message message, byte[] bytes) {
        try {
            log.info(new String(message.getBody()));
            // 从服务器获取Json字符串
            String messageBody = new String(message.getBody());
            // 声明中间变量进行处理
            String fly = StringUtils.replaceAll(messageBody, "\\\\", "");
            // 处理完后赋值回去
            messageBody = StringUtils.substring(fly, 1, messageBody.length() - 1);
            // 反序列化Json为对象
            MessageDTO messageDTO = objectMapper.readValue(messageBody, MessageDTO.class);
            if (messageDTO != null) {
                log.info("Channel: {}, Message: {}", new String(message.getChannel()), messageDTO.getBody());
            }
        } catch (IOException e) {
            log.error("Couldn't convert json", e);
        }
    }
}
