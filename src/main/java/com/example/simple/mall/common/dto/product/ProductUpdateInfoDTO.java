package com.example.simple.mall.common.dto.product;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 商品信息更新表
 *
 * @author sunny
 * @since 2025/05/31
 */
@Data
public class ProductUpdateInfoDTO {

    /**
     * 更新时间
     */
    public String updateTime;

    /**
     * 所属商品编码
     */
    @NotNull(message = "所属商品编码不能为空")
    public String productCode;

    /**
     * 商品表ID
     */
    @NotNull(message = "商品表ID不能为空")
    public Long id;

    /**
     * 商品分类id
     */
    @NotNull(message = "商品分类id不能为空")
    public Long productCategoryId;

    /**
     * 商品标题
     */
    public String productName;

    /**
     * 商品描述（详情）
     */
    public String description;

    /**
     * 品牌
     */
    public String brand;

    /**
     * 上下架状态（1：上架；2:下架）
     */
    public Integer status;


    /**
     * 商品详细表ID
     */
    public Long productDetailsId;

    /**
     * 商品数量
     */
    public Integer productQuantity;

    /**
     * 商品单价
     */
    public BigDecimal productPrice;

    /**
     * 商品图片
     */
    public String productImg;

    /**
     * 材质
     */
    public String material;

    /**
     * 尺寸
     */
    public String size;

    /**
     * 版本
     */
    public Integer version;


    /**
     * 商品图片信息
     */
    public List<ProductImages> productImagesList = new ArrayList<>();


    @Data
    public static class ProductImages {
        /**
         * 如对应变体时填写
         */
        public Long variantId;

        /**
         * url
         */
        public String url;

        /**
         * 显示顺序
         */
        public Integer sortOrder;
    }
}
