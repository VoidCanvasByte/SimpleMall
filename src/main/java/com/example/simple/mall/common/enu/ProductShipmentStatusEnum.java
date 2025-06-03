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
public enum ProductShipmentStatusEnum {

    IN_PROCESS(1, "运输中"),
    FINISH(2, "已签收");

    private final Integer code;
    private final String msg;
}
