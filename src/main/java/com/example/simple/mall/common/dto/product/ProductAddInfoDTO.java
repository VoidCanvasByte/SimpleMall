package com.example.simple.mall.common.dto.product;

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
    @NotNull(message = "商品分类ID不能为空")
    public Long productCategoryId;

    /**
     * 商品名称
     */
    @NotNull(message = "商品名称不能为空")
    public String productName;

    /**
     * 商品货号
     */
    @NotNull(message = "商品货号不能为空")
    public String productCode;

    /**
     * 商品描述
     */
    public String productDesc;

    /**
     * 品牌
     */
    @NotNull(message = "品牌不能为空")
    public String brand;

    /**
     * 上下架状态（1：上架，2：下架）
     */
    public Integer status;

    /**
     * 商品数量
     */
    @NotNull(message = "商品数量不能为空")
    public Integer productQuantity;

    /**
     * 商品单价
     */
    @NotNull(message = "商品单价不能为空")
    public BigDecimal productPrice;

    /**
     * 变体ID
     */
    @NotNull(message = "商品变体ID不能为空")
    public Long variantId;

    /**
     * 商品图片
     */
    public List<String> productImgList;

    /**
     * 材质
     */
    public String material;

    /**
     * 尺寸
     */
    public String size;
}
