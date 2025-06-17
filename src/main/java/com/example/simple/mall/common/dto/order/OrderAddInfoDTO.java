package com.example.simple.mall.common.dto.order;

import com.example.simple.mall.common.dto.user.UserBaseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * 订单创建DTO
 *
 * @author sunny
 * @since 2025/05/24
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class OrderAddInfoDTO extends UserBaseDTO {

    /**
     * 用户id
     */
    @Schema(description = "用户id")
    @NotNull(message = "用户id不能为空")
    public Long userId;

    /**
     * 收获地址ID
     */
    @Schema(description = "收获地址ID")
    @NotNull(message = "收获地址ID")
    public Long shippingAddressId;

    /**
     * 产品ID
     */
    @Schema(description = "产品ID")
    @NotNull(message = "产品ID不能为空")
    public Long productId;

    /**
     * 产品code
     */
    @Schema(description = "产品code")
    @NotBlank(message = "产品code不能为空")
    public String productCode;

    /**
     * 产品数量
     */
    @Schema(description = "产品数量")
    @NotNull(message = "商品数量不能为空")
    @DecimalMin(value = "1", message = "商品数量不能小于1")
    public Integer quantity;

    /**
     * 商品变体ID
     */
    @Schema(description = "商品变体ID")
    @NotNull(message = "商品变体ID不能为空")
    public Long variantId;

    /**
     * 下单时单价
     */
    @Schema(description = "下单时单价")
    @NotNull(message = "下单时单价")
    @DecimalMin("0.01")
    public BigDecimal unitPrice;
}