package com.example.simple.mall.common.dto.product;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 商品分页查询条件
 *
 * @author sunny
 * @since 2025/06/17
 */
@Data
public class ProductPaginationDTO {

    /**
     * 商品分类名称
     */
    @Schema(description = "商品分类名称")
    public String productCategoryName;

    /**
     * 商品名称
     */
    @Schema(description = "商品名称")
    public String productName;
}