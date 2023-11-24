package com.cxylk.chatgpt.common;

/**
 * @author likui
 * @description
 * @date 2023/11/16 16:39
 **/
public class Constants {
    public static final String NULL="NULL";

    /**
     * 官方支持的请求角色类型：https://platform.openai.com/docs/guides/chat/introduction
     */
    public enum Role{
        SYSTEM("system"),

        USER("user"),

        ASSISTANT("assistant"),
        ;

        Role(String code) {
            this.code = code;
        }

        public String getCode() {
            return code;
        }

        private String code;

    }
}
