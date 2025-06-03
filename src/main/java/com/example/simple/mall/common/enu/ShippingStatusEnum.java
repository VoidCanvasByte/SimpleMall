package com.example.simple.mall.common.enu;


import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 发货状态枚举
 *
 * @author sunny
 * @since 2025/06/03
 */
@Getter
@AllArgsConstructor
public enum ShippingStatusEnum {

    NOT_SHIPPED("未发货", 1),
    SHIPPED("已发货", 2);
    private final String msg;
    private final Integer code;
}
