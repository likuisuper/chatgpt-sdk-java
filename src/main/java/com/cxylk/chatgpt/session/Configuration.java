package com.cxylk.chatgpt.session;

import com.cxylk.chatgpt.IOpenAiApi;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import okhttp3.OkHttpClient;
import okhttp3.sse.EventSource;
import okhttp3.sse.EventSources;
import org.jetbrains.annotations.NotNull;

/**
 * @author likui
 * @description 配置信息
 * @date 2023/11/17 13:56
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Configuration {
    private IOpenAiApi openAiApi;

    private OkHttpClient httpClient;

    private String apiHost;

    @NotNull
    private String apiKey;

    public EventSource.Factory createRequestFactory(){
        return EventSources.createFactory(httpClient);
    }
}
