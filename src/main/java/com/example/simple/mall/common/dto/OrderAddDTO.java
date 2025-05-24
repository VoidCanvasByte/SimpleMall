package com.example.simple.mall.common.dto;

import lombok.Data;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;

/**
 * 订单创建
 *
 * @author sunny
 * @since 2025/05/24
 */
@Data
public class OrderAddDTO {

    /**
     * 用户id
     */
    @NotEmpty(message = "用户id不能为空")
    public String userId;

    /**
     * 产品ID
     */
    @NotEmpty(message = "产品ID不能为空")
    public String productId;

    /**
     * 产品数量
     */
    @Min(0)
    public BigDecimal quantity;

    /**
     * 购物车主表ID
     */
    @NotEmpty(message = "购物车主表ID不能为空")
    public Integer cartItemId;
}