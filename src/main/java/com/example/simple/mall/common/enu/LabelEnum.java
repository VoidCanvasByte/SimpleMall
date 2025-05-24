package com.example.simple.mall.common.enu;

import lombok.Getter;

/**
 * 购物车添加枚举
 *
 * @author sunny
 * @since 2025/05/15
 */
@Getter
public enum LabelEnum {

    ADD(1, "购物车添加数量"),
    REDUCE(2, "购物车减少数量");

    private final Integer code;
    private final String msg;

    LabelEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
