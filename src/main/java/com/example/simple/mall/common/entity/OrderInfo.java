package com.example.simple.mall.common.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * 商品订单明细表
 *
 * @author sunny
 * @since 2025/05/09
 */
@Data
@TableName("order_info")
@EqualsAndHashCode(callSuper = true)
public class OrderInfo extends BaseEntity {

    /**
     * 订单ID
     */
    @TableField("order_id")
    public Integer orderId;

    /**
     * 产品ID
     */
    @TableField("product_id")
    public Integer productId;

    /**
     * 产品名字
     */
    @TableField("product_name")
    public Integer productName;

    /**
     * 产品价格
     */
    @TableField("product_price")
    public BigDecimal productPrice;

    /**
     * 产品数量
     */
    @TableField("quantity")
    public Integer quantity;
}