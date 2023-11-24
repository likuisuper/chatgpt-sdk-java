package com.cxylk.chatgpt.domain.other;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * @author likui
 * @description 完成请求的使用情况统计信息
 * @date 2023/11/16 14:20
 **/
public class Usage implements Serializable {
    /**
     * 完成令牌数
     */
    @JsonProperty("completion_tokens")
    private long completionTokens;

    /**
     * 提示令牌数
     */
    @JsonProperty("prompt_tokens")
    private long promptTokens;

    /**
     * 使用令牌数=提示+完成令牌数
     */
    @JsonProperty("total_tokens")
    private long totalTokens;

    public long getCompletionTokens() {
        return completionTokens;
    }

    public void setCompletionTokens(long completionTokens) {
        this.completionTokens = completionTokens;
    }

    public long getPromptTokens() {
        return promptTokens;
    }

    public void setPromptTokens(long promptTokens) {
        this.promptTokens = promptTokens;
    }

    public long getTotalTokens() {
        return totalTokens;
    }

    public void setTotalTokens(long totalTokens) {
        this.totalTokens = totalTokens;
    }
}
