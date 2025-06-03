package com.example.simple.mall.common.dto.order;

import com.example.simple.mall.common.dto.user.UserBaseDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 物流订单输入信息
 *
 * @author sunny
 * @since 2025/06/03
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class OrderMainLogisticsInfoDTO extends UserBaseDTO {

    /**
     * 订单ID
     */
    @NotNull(message = "订单ID不能为空")
    public Long orderId;

    /**
     * 物流公司
     */
    @NotBlank(message = "物流公司不能为空")
    public String shippingCompany;

    /**
     * 物流单号
     */
    @NotBlank(message = "物流单号不能为空")
    public String trackingNumber;

    /**
     * 发货时间
     */
    @NotBlank(message = "发货时间不能为空")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd-HH:mm:ss")
    public Date deliveryTime;

    /**
     * 物流状态（1待揽件，2运输中，3派送中，4已签收）
     */
    @NotNull(message = "物流状态不能为空")
    public Integer status;
}
