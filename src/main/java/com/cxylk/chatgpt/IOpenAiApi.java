package com.cxylk.chatgpt;

import com.cxylk.chatgpt.domain.chat.ChatCompletionRequest;
import com.cxylk.chatgpt.domain.chat.ChatCompletionResponse;
import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * @author likui
 * @description
 * @date 2023/11/16 20:20
 **/
public interface IOpenAiApi {
    String v1_completions="/v1/completions";

    String v1_chat_completions="/v1/chat/completions";

    /**
     * 问答模型 默认3-5模型
     * @param completionRequest 请求信息
     * @return 应答结果
     */
    @POST(v1_chat_completions)
    Single<ChatCompletionResponse> completions(@Body ChatCompletionRequest completionRequest);
}
