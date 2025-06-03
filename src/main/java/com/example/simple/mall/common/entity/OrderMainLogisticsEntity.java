package com.example.simple.mall.common.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

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
    public Long id;

    /**
     * 订单ID
     */
    @TableField("order_id")
    public Long orderId;

    /**
     * 物流公司
     */
    @TableField("shipping_company")
    public String shippingCompany;

    /**
     * 物流单号
     */
    @TableField("tracking_number")
    public String trackingNumber;

    /**
     * 发货时间
     */
    @TableField("delivery_time")
    public Date deliveryTime;

    /**
     * 物流状态（1待揽件，2运输中，3派送中，4已签收）
     */
    @TableField("status")
    public Integer status;

    /**
     * 创建时间
     */
    @TableField("create_time")
    public Date createTime;

    /**
     * 更新时间
     */
    @TableField("update_time")
    public Date updateTime;
}
