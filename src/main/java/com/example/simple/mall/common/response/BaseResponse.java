package com.example.simple.mall.common.response;

import lombok.Getter;
import lombok.Setter;

/**
 * 基础响应
 *
 * @author sunny
 * @since 2025/05/05
 */
@Getter
@Setter
public class BaseResponse {
    Integer code;
    String message;

    public BaseResponse(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}