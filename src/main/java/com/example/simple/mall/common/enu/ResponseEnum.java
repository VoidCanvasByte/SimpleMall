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

    SUCCESS(200, "操作成功"),
    FAIL(500, "操作失败"),
    // -----------------------------------------用户相关---------------------------------------------------------------------------------
    USER_NOT_EXIST(1001, "用户不存在"),
    USER_EMAIL_EXIST(1002, "当前用户邮箱已经注册，请确认邮箱信息"),
    USER_PASSWORD_ERROR(1003, "用户名或密码错误，请确认信息"),
    USER_NOT_LOGIN(1004, "用户未登录"),
    USER_LOGIN_ERROR(1005, "用户登录失败"),
    USER_ID_IS_EMPTY(1006, "用户ID不能为空，请确认信息"),
    USER_STATUS_IS_UNUSED(1007, "用户为封存状态，不可以进行信息的更新"),
    USER_EMAIL_IF_CORRECT(1008, "当前邮箱格式不正确，请确认邮箱的是否真确！"),
    USER_PASSWORD_IS_WRONG(1009, "当前密码输入错误，请确定密码！"),

    // -----------------------------------------购物车相关---------------------------------------------------------------------------------
    USER_CART_IS_EMPTY(10010, "当前用户购物车中不存在该物品，请确认数据"),

    // -----------------------------------------订单相关---------------------------------------------------------------------------------
    PRODUCT_NOT_EXIST(10011, "商当前商品信息不存在或者商品库存不足，请确认商品信息"),
    ORDER_NOT_EXIST(10012, "当前订单信息不存在，请确认信息"),

    // -----------------------------------------产品相关---------------------------------------------------------------------------------
    PRODUCT_CATEGORY_NOT_EXIST(10013,"当前商品分类不存在请确认数据！");
    @Setter
    private Integer code;
    @Setter
    private String message;
}
