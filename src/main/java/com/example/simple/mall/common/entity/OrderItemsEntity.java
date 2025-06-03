package com.example.simple.mall.common.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 商品订单明细表
 *
 * @author sunny
 * @since 2025/05/09
 */
@Data
@TableName("order_main_items")
public class OrderItemsEntity {

    /**
     * 用户ID
     */
    @TableId(value = "id")
    private String id;

    /**
     * 所属订单
     */
    @TableField("order_id")
    public Long orderId;

    /**
     * 商品变体
     */
    @TableField("variant_id")
    public Long variantId;

    /**
     * 下单时商品名称
     */
    @TableField("product_name")
    public String productName;

    /**
     * 下单时 SKU
     */
    @TableField("sku")
    public String sku;

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

    /**
     * 创建时间
     */
    @TableField("create_time")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd-HH:mm:ss")
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField("update_time")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd-HH:mm:ss")
    private Date updateTime;

}