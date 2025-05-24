package com.example.simple.mall.common.enu;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 订单枚举
 *
 * @author sunny
 * @since 2025/05/24
 */
@Getter
@AllArgsConstructor
public enum ProductStatusEnum {
    NORMAL(1, "正常"),
    SOLD_OUT(2, "售罄");

    private final Integer code;
    private final String msg;
}
