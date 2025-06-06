package com.example.simple.mall.common.dto;


import lombok.Data;


/**
 * checkToken响应体
 *
 * @author sunny
 * @since 2025/06/06
 */
@Data
public class R<T> {

    /**
     * boolean
     */
    private boolean success;

    /**
     * message
     */
    private String message;

    /**
     * data
     */
    private T data;

    public static <T> R<T> ok(T data) {
        R<T> result = new R<>();
        result.setSuccess(true);
        result.setMessage("success");
        result.setData(data);
        return result;
    }

    public static <T> R<T> ok(String message, T data) {
        R<T> result = new R<>();
        result.setSuccess(true);
        result.setMessage(message);
        result.setData(data);
        return result;
    }

    public static <T> R<T> error(String message) {
        R<T> result = new R<>();
        result.setSuccess(false);
        result.setMessage(message);
        result.setData(null);
        return result;
    }
}