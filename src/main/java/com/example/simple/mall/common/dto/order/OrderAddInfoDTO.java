package com.example.simple.mall.common.dto.order;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

/**
 * 订单创建DTO
 *
 * @author sunny
 * @since 2025/05/24
 */
@Data
public class OrderAddInfoDTO {

    /**
     * 用户id
     */
    @NotNull(message = "用户id不能为空")
    public Integer userId;

    /**
     * 收获地址ID
     */
    @NotNull(message = "收获地址ID")
    public Integer shippingAddressId;

    @NotEmpty(message = "商品信息不能为空")
    private List<ProductInfo> productInfoList;

    @Data
    public static class ProductInfo {
        /**
         * 产品ID
         */
        @NotNull(message = "产品ID不能为空")
        public Integer productId;

        /**
         * 产品数量
         */
        @NotNull(message = "商品数量不能为空")
        @DecimalMin(value = "1", message = "商品数量不能小于1")
        public Integer quantity;

        /**
         * 商品变体ID不能为空
         */
        @NotNull(message = "商品变体ID不能为空")
        public Integer variantId;

        /**
         * 下单时单价
         */
        @NotNull(message = "下单时单价")
        @DecimalMin("0.01")
        public BigDecimal unitPrice;
    }
}