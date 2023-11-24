package com.cxylk.chatgpt.domain.chat;

import lombok.Data;

import java.util.List;

/**
 * @author likui
 * @description 会话返回响应
 * eg:
 *  {
 *   "id": "chatcmpl-123",
 *   "object": "chat.completion",
 *   "created": 1677652288,
 *   "model": "gpt-3.5-turbo-0613",
 *   "system_fingerprint": "fp_44709d6fcb",
 *   "choices": [{
 *     "index": 0,
 *     "message": {
 *       "role": "assistant",
 *       "content": "\n\nHello there, how may I assist you today?",
 *     },
 *     "finish_reason": "stop"
 *   }],
 *   "usage": {
 *     "prompt_tokens": 9,
 *     "completion_tokens": 12,
 *     "total_tokens": 21
 *   }
 * }
 * @date 2023/11/16 14:03
 **/
@Data
public class ChatCompletionResponse {
    private String id;

    /**
     * 始终为chat.completion
     */
    private String object;

    /**
     * 创建会话完成时的时间戳
     */
    private long created;

    /**
     * 会话使用的模型
     */
    private String model;

    /**
     * 消息列表
     */
    private List<ChatChoices> choices;


}
