package com.example.simple.mall.common.dto.order;

import lombok.Data;

/**
 * 订单物流信息返回信息
 *
 * @author sunny
 * @since 2025/06/03
 */
@Data
public class OrderMainLogisticsReDTO {

    /**
     * id
     */
    public Long id;

    /**
     * 订单ID
     */
    public Long orderId;

    /**
     * 物流公司
     */
    public String shippingCompany;

    /**
     * 物流单号
     */
    public String trackingNumber;

    /**
     * 发货时间
     */
    public String deliveryTime;

    /**
     * 物流状态（1待揽件，2运输中，3派送中，4已签收）
     */
    public Integer status;

    /**
     * 创建时间
     */
    public String createTime;

    /**
     * 更新时间
     */
    public String updateTime;
}
