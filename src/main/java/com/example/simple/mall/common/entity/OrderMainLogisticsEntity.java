package com.example.simple.mall.common.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * 订单物流信息表
 *
 * @author sunny
 * @since 2025-06-03
 */
@Data
@TableName("order_main_logistics")
public class OrderMainLogisticsEntity {


    /**
     * id
     */
    @TableField("id")
    private Long id;

    /**
     * 订单ID
     */
    @TableField("order_id")
    private Long orderId;

    /**
     * 物流公司
     */
    @TableField("shipping_company")
    private String shippingCompany;

    /**
     * 物流单号
     */
    @TableField("tracking_number")
    private String trackingNumber;

    /**
     * 发货时间
     */
    @TableField("delivery_time")
    private String deliveryTime;

    /**
     * 物流状态（1待揽件，2运输中，3派送中，4已签收）
     */
    @TableField("status")
    private Integer status;

    /**
     * 创建时间
     */
    @TableField("create_time")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd-HH:mm:ss")
    private String createTime;

    /**
     * 更新时间
     */
    @TableField("update_time")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd-HH:mm:ss")
    private String updateTime;
}
