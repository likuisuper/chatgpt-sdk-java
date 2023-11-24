package com.cxylk.chatgpt.interceptor;

import cn.hutool.http.ContentType;
import cn.hutool.http.Header;
import com.alibaba.fastjson.JSON;
import com.cxylk.chatgpt.common.Constants;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

/**
 * @author likui
 * @description 拦截器，实现Interceptor的拦截器会放入拦截器链的第一个，一般用来设置一些请求头信息
 * @date 2023/11/17 14:27
 **/
public class OpenAiInterceptor implements Interceptor {

    /**
     * openai key，官网申请
     */
    private final String apiKeyBySystem;

    public OpenAiInterceptor(String apiKeyBySystem) {
        this.apiKeyBySystem = apiKeyBySystem;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        // 1、获取原始的request，每个http请求都会被拦截
        Request original = chain.request();
        String apiKeyByUser = original.header("apiKey");
        // 2、获取key,优先使用自己的key
        String apiKey = Constants.NULL.equals(apiKeyByUser) ? apiKeyBySystem : apiKeyByUser;
        // 3、重新构建request
        Request request = original.newBuilder()
                .url(original.url())
                .header(Header.AUTHORIZATION.getValue(), "Bearer " + apiKey)
                .header(Header.CONTENT_TYPE.getValue(), ContentType.JSON.getValue())
                .method(original.method(), original.body())
                .build();
        // 4、返回执行结果
        System.out.println(request.toString());
        return chain.proceed(request);
    }
}
