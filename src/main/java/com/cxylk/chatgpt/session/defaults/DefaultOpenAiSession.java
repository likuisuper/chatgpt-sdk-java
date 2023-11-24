package com.cxylk.chatgpt.session.defaults;

import cn.hutool.http.ContentType;
import com.cxylk.chatgpt.IOpenAiApi;
import com.cxylk.chatgpt.common.Constants;
import com.cxylk.chatgpt.domain.chat.ChatCompletionRequest;
import com.cxylk.chatgpt.domain.chat.ChatCompletionResponse;
import com.cxylk.chatgpt.session.Configuration;
import com.cxylk.chatgpt.session.OpenAiSession;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.sse.EventSource;
import okhttp3.sse.EventSourceListener;

/**
 * @author likui
 * @description
 * @date 2023/11/17 10:06
 **/
public class DefaultOpenAiSession implements OpenAiSession {

    private IOpenAiApi openAiApi;

    private Configuration configuration;

    private EventSource.Factory factory;

    public DefaultOpenAiSession(Configuration configuration) {
        this.configuration = configuration;
        this.openAiApi = configuration.getOpenAiApi();
        factory=configuration.createRequestFactory();
    }

    @Override
    public ChatCompletionResponse completions(ChatCompletionRequest chatCompletionRequest) {
        return this.openAiApi.completions(chatCompletionRequest).blockingGet();
    }

    @Override
    public EventSource chatCompletions(ChatCompletionRequest chatCompletionRequest, EventSourceListener eventSourceListener) throws JsonProcessingException {
        return chatCompletions(Constants.NULL,Constants.NULL,chatCompletionRequest,eventSourceListener);
    }

    @Override
    public EventSource chatCompletions(String apiHostByUser, String apiKeyByUser, ChatCompletionRequest completionRequest, EventSourceListener eventSourceListener) throws JsonProcessingException {
        if (!completionRequest.getStream()) {
            throw new RuntimeException("illegal parameter stream is false!");
        }

        // 动态设置 Host、Key，便于用户传递自己的信息
        String apiHost = Constants.NULL.equals(apiHostByUser) ? configuration.getApiHost() : apiHostByUser;
        String apiKey = Constants.NULL.equals(apiKeyByUser) ? configuration.getApiKey() : apiKeyByUser;

        // 构建请求信息
        Request request = new Request.Builder()
                // url: https://api.openai.com/v1/chat/completions - 通过 IOpenAiApi 配置的 POST 接口，用这样的方式从统一的地方获取配置信息
                .url(apiHost.concat(IOpenAiApi.v1_chat_completions))
                // 这里加入apiKey是为了在OpenAiInterceptor中获取然后放入header中
                .addHeader("apiKey", apiKey)
                .post(RequestBody.create(MediaType.parse(ContentType.JSON.getValue()), new ObjectMapper().writeValueAsString(completionRequest)))
                .build();
        // 返回结果信息；eventSourceListener可以进行事件监听，EventSource 对象可以取消应答
        return factory.newEventSource(request,eventSourceListener);
    }
}
