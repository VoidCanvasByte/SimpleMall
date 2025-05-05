package com.example.simple.mall.common.enu;

import lombok.Getter;

/**
 * 用户状态枚举
 *
 * @author sunny
 * @since 2025/05/05
 */
@Getter
public enum UserStatusEnum {

    FRIEND(1, "用户状态正常"),
    SEAL(2, "用户禁封");

    private final Integer code;
    private final String msg;

    UserStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}