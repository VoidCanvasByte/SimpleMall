package com.example.simple.mall.common.exception.response;

import com.example.simple.mall.common.enu.ResponseEnum;
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
    private Integer code;
    private String message;

    public BaseResponse(ResponseEnum code) {
        this.code = code.getCode();
        this.message = code.getMessage();
    }
}
