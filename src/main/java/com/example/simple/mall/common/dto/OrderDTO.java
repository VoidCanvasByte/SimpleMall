package com.example.simple.mall.common.dto;

import com.example.simple.mall.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * 订单信息DTO
 *
 * @author sunny
 * @since 2025/05/09
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class OrderDTO extends BaseEntity {

    /**
     * 用户id
     */
    public String userId;

    /**
     * 数量
     */
    public String totalAmount;

    /**
     * 订单状态
     */
    public String status;

    /**
     * 订单ID
     */
    public Integer orderId;

    /**
     * 产品ID
     */
    public Integer productId;

    /**
     * 产品名字
     */
    public Integer productName;

    /**
     * 产品价格
     */
    public BigDecimal productPrice;

    /**
     * 产品数量
     */
    public Integer quantity;
}
