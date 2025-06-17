package com.example.simple.mall.common.dto.product;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.math.BigDecimal;

/**
 * 返回
 *
 * @author sunny
 * @since 2025/06/03
 */
@Data
public class ProductReDTO {

    /**
     * 商品code
     */
    @Schema(description = "商品code")
    public String productCode;

    /**
     * 商品分类表
     */
    @Schema(description = "商品分类表ID")
    public Long productCategoryId;

    /**
     * 商品标题
     */
    @Schema(description = "商品标题名称")
    public String productName;

    /**
     * 上下架状态（1：上架，2：下架）
     */
    @Schema(description = "上下架状态（1：上架，2：下架）")
    public Integer status;

    /**
     * 商品价格
     */
    @Schema(description = "商品价格")
    public BigDecimal productPrice;

    /**
     * 商品详情ID
     */
    @Schema(description = "商品详情ID")
    public Long productDetailsId;
}
