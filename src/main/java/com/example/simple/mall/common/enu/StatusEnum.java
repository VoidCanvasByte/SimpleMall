package com.example.simple.mall.common.enu;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

/**
 * 基础状态
 *
 * @author sunny
 * @since 2025/05/30
 */
@Getter
@AllArgsConstructor
public enum StatusEnum {
    NEW("新建", 1),
    FINISH("完成", 2),
    CANCEL("取消", 5);
    private final String msg;
    private final Integer code;
}
