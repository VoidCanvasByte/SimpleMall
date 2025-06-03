package com.example.simple.mall.common.dto.product;

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
    public String productCode;

    /**
     * 商品分类表
     */
    public Long productCategoryId;

    /**
     * 商品标题
     */
    public String productName;

    /**
     * 上下架状态（1：上架，2：下架）
     */
    public Integer status;

    /**
     * 商品价格
     */
    public BigDecimal productPrice;

    /**
     * 商品详情ID
     */
    public Long productDetailsId;
}
