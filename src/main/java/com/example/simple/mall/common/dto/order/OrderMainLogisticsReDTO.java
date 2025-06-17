package com.example.simple.mall.common.dto.order;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

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
    @Schema(description = "id")
    public Long id;

    /**
     * 订单ID
     */
    @Schema(description = "订单ID")
    public Long orderId;

    /**
     * 物流公司
     */
    @Schema(description = "物流公司")
    public String shippingCompany;

    /**
     * 物流单号
     */
    @Schema(description = "物流单号")
    public String trackingNumber;

    /**
     * 发货时间
     */
    @Schema(description = "发货时间")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd-HH:mm:ss")
    public Date deliveryTime;

    /**
     * 物流状态（1待揽件，2运输中，3派送中，4已签收）
     */
    @Schema(description = "物流状态（1待揽件，2运输中，3派送中，4已签收）")
    public Integer status;

    /**
     * 更新时间
     */
    @Schema(description = "更新时间")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd-HH:mm:ss")
    public Date updateTime;
}
