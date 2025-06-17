package com.example.simple.mall.common.dto.product;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * 商品DTO
 *
 * @author sunny
 * @since 2025/05/08
 */
@Data
public class ProductAddInfoDTO {

    /**
     * 商品分类ID
     */
    @Schema(description = "商品分类ID")
    @NotNull(message = "商品分类ID不能为空")
    public Long productCategoryId;

    /**
     * 商品名称
     */
    @Schema(description = "商品名称")
    @NotNull(message = "商品名称不能为空")
    public String productName;

    /**
     * 商品货号
     */
    @Schema(description = "商品货号")
    @NotNull(message = "商品货号不能为空")
    public String productCode;

    /**
     * 商品描述
     */
    @Schema(description = "商品描述")
    public String productDesc;

    /**
     * 品牌
     */
    @Schema(description = "品牌")
    @NotNull(message = "品牌不能为空")
    public String brand;

    /**
     * 上下架状态（1：上架，2：下架）
     */
    @Schema(description = "上下架状态（1：上架，2：下架）")
    public Integer status;

    /**
     * 商品数量
     */
    @Schema(description = "商品数量")
    @NotNull(message = "商品数量不能为空")
    public Integer productQuantity;

    /**
     * 商品单价
     */
    @Schema(description = "商品单价")
    @NotNull(message = "商品单价不能为空")
    public BigDecimal productPrice;

    /**
     * 材质
     */
    @Schema(description = "材质")
    public String material;

    /**
     * 尺寸
     */
    @Schema(description = "尺寸")
    public Integer size;

    /**
     * 商品图片
     */
    @Schema(description = "商品图片")
    public List<ProductImg> productImgList;

    /**
     * 图片合集
     *
     * @author sunny
     * @since 2025/06/06
     */
    @Schema(description = "图片合集")
    @Data
    public static class ProductImg {

        /**
         * 排序
         */
        @Schema(description = "排序")
        public Integer sortOrder;

        /**
         * 地址
         */
        @Schema(description = "地址")
        public String url;
    }
}
