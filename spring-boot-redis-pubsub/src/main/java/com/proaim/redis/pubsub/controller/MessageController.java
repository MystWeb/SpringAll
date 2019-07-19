package com.proaim.redis.pubsub.controller;

import com.proaim.redis.pubsub.service.MessagePublisher;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: ziming.xing
 * @date: 2019/7/19 14:51
 */
@Slf4j
@RestController
@Api(value = "消息控制器", tags = "消息控制器")
public class MessageController {
    @Autowired
    private final MessagePublisher messagePublisher;

    public MessageController(MessagePublisher messagePublisher) {
        this.messagePublisher = messagePublisher;
    }

    @PostMapping("/send")
    @ApiOperation(value = "发送消息", notes = "发送消息")
    @ApiImplicitParam(name = "message", value = "消息")
    public ResponseEntity<String> sendMessage(@RequestBody String message) {
        messagePublisher.publish(message);
        return ResponseEntity.ok(message);
    }
}
