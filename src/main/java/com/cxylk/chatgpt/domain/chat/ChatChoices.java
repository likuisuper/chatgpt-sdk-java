package com.cxylk.chatgpt.domain.chat;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author likui
 * @description 对话消息
 * @date 2023/11/16 14:05
 **/
@Data
public class ChatChoices {
    /**
     * 停止生成原因
     */
    @JsonProperty("finish_reason")
    private String finishReason;

    /**
     * 索引
     */
    private Integer index;

    /**
     * stream=true,返回字段为delta
     */
    @JsonProperty("delta")
    private Message delta;

    /**
     * stream=false,返回字段为message
     */
    @JsonProperty("message")
    private Message message;
}
