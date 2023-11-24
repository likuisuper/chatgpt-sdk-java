package com.cxylk.chatgpt;

import com.cxylk.chatgpt.common.Constants;
import com.cxylk.chatgpt.domain.chat.ChatCompletionRequest;
import com.cxylk.chatgpt.domain.chat.Message;
import com.cxylk.chatgpt.session.Configuration;
import com.cxylk.chatgpt.session.OpenAiSession;
import com.cxylk.chatgpt.session.OpenAiSessionFactory;
import com.cxylk.chatgpt.session.defaults.DefaultOpenAiSessionFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Response;
import okhttp3.sse.EventSource;
import okhttp3.sse.EventSourceListener;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.concurrent.CountDownLatch;

/**
 * @author likui
 * @description
 * @date 2023/11/17 14:43
 **/
@Slf4j
public class BusyTest {
    private OpenAiSession aiSession;

    @Before
    public void before() {
        //1、初始化配置
        Configuration configuration = new Configuration();
        configuration.setApiHost("https://api.openai.com");
        configuration.setApiKey("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");
        //2、构建会话工厂
        OpenAiSessionFactory openAiSessionFactory = new DefaultOpenAiSessionFactory(configuration);
        //3、开启会话
        aiSession = openAiSessionFactory.openAiSession();
    }

    @Test
    public void chat() throws JsonProcessingException, InterruptedException {
        //1、创建参数
        ChatCompletionRequest completionRequest = ChatCompletionRequest.builder()
                .stream(true)
                .model(ChatCompletionRequest.Model.GPT_3_5_TURBO.getCode())
                .maxTokens(1024)
                .messages(Collections.singletonList(Message.builder().role(Constants.Role.USER).content("1+1").build())).build();
        //2、发起请求
        aiSession.chatCompletions(completionRequest, new EventSourceListener() {

            @Override
            public void onEvent(EventSource eventSource, String id, String type, String data) {
                log.info("测试结果 id:{} type:{} data:{}",id,type,data);
            }

            @Override
            public void onFailure(EventSource eventSource, Throwable t, Response response) {
                System.out.println("失败原因:"+response.message());
                log.error("失败 code:{} message:{}",response.code(),response.message());
            }
        });

        //等待
        new CountDownLatch(1).await();
    }
}
