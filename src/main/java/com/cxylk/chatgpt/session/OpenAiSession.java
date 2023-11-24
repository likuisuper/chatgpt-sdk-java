package com.cxylk.chatgpt.session;

import com.cxylk.chatgpt.domain.chat.ChatCompletionRequest;
import com.cxylk.chatgpt.domain.chat.ChatCompletionResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import okhttp3.sse.EventSource;
import okhttp3.sse.EventSourceListener;

/**
 * @author likui
 * @description
 * @date 2023/11/17 10:03
 **/
public interface OpenAiSession {
    /**
     * 问答模型 3.5/4.0
     * @param chatCompletionRequest
     * @return
     */
    ChatCompletionResponse completions(ChatCompletionRequest chatCompletionRequest);

    /**
     * 问答模型 3.5/4.0 流式反馈
     * @param chatCompletionRequest 请求信息
     * @param eventSourceListener 实现监听，通过监听onEvent方法接收数据
     * @return 应答结果
     */
    EventSource chatCompletions(ChatCompletionRequest chatCompletionRequest, EventSourceListener eventSourceListener) throws JsonProcessingException;

    EventSource chatCompletions(String apiHostByUser, String apiKeyByUser, ChatCompletionRequest completionRequest, EventSourceListener eventSourceListener) throws JsonProcessingException;
}
