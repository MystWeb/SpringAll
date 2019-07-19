package com.proaim.redis.pubsub.model.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessageDTO implements Serializable {
    private static final long serialVersionUID = -1795155869919279097L;
    /**
     * 消息内容
     */
    private String body;

    /**
     * 接收人
     */
    private String[] receivers;
}
