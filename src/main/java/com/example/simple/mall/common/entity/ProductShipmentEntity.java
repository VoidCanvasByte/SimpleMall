package com.example.simple.mall.common.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * 发货表
 *
 * @author sunny
 * @since 2025/06/03
 */
@Data
@TableName("product_shipment")
public class ProductShipmentEntity {

    /**
     * 发货主键
     */
    @TableField(value = "id")
    public Long id;

    /**
     * 关联订单ID
     */
    @TableField(value = "order_id")
    public Long orderId;

    /**
     * 运单号
     */
    @TableField(value = "tracking_number")
    public String trackingNumber;

    /**
     * 发货时间
     */
    @TableField(value = "shipped_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd-HH:mm:ss")
    public Date shippedAt;

    /**
     * 签收时间
     */
    @TableField(value = " delivered_at ")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd-HH:mm:ss")
    public Date deliveredAt;

    /**
     * 发货状态（0：运输中，1：已签收）
     */
    public Integer status;
}
