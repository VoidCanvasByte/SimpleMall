package com.example.simple.mall.common.enu;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * 响应的枚举
 *
 * @author sunny
 * @since 2025/05/05
 */
@Getter
@AllArgsConstructor
public enum ResponseEnum {

    SUCCESS(200, "操作成功"), FAIL(500, "操作失败"),
    // -----------------------------------------用户相关---------------------------------------------------------------------------------
    USER_NOT_EXIST(1001, "用户不存在"),
    USER_EXIST(1002, "当前用户邮箱已经注册，请确认邮箱信息"),
    USER_PASSWORD_ERROR(1003, "用户名或密码错误，请确认信息"),
    USER_NOT_LOGIN(1004, "用户未登录"),
    USER_LOGIN_ERROR(1005, "用户登录失败"),
    ;

    @Setter
    private Integer code;
    @Setter
    private String message;
}
