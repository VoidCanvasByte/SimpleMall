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
@TableName("order_items")
@EqualsAndHashCode(callSuper = true)
public class OrderItems extends BaseEntity {

    /**
     * 所属订单
     */
    @TableField("order_id")
    public Integer orderId;

    /**
     * 商品变体
     */
    @TableField("variant_id")
    public Integer variantId;

    /**
     * 下单时商品名称
     */
    @TableField("product_name")
    public String productName;

    /**
     * 下单时 SKU
     */
    @TableField("sku")
    public Integer sku;

    /**
     * 下单时单价
     */
    @TableField("unit_price")
    public BigDecimal unitPrice;

    /**
     * 数量
     */
    @TableField("quantity")
    public Integer quantity;

    /**
     * 小计 = (unit_price * quantity)
     */
    @TableField("subtotal")
    public BigDecimal subtotal;
}