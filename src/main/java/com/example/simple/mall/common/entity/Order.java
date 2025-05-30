package com.example.simple.mall.common.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.math.BigDecimal;

/**
 * 订单表
 *
 * @author sunny
 * @since 2025/05/09
 */
@Data
@TableName("order")
@EqualsAndHashCode(callSuper = true)
public class Order extends BaseEntity {

    /**
     * 订单编号
     */
    @TableField("order_number")
    public String orderNumber;

    /**
     * 下单用户
     */
    @TableField("user_id")
    public Integer userId;

    /**
     * 收货地址
     */
    @TableField("shipping_address_id")
    public Integer shippingAddressId;

    /**
     * 订单总额
     */
    @TableField("total_amount")
    public BigDecimal totalAmount;

    /**
     * 支付状态（1：未支付，2：已支付）
     */
    @TableField("payment_status")
    public Integer paymentStatus;

    /**
     * 发货状态（3：未发货，4：已发货）
     */
    @TableField("shipping_status")
    public Integer shippingStatus;

    /**
     * 订单状态（1：新建，2：完成，3：取消）
     */
    @TableField("status")
    public Integer status;
}