package com.example.simple.mall.common.dto.product;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * ProductCategoryInfoDTO
 *
 * @author sunny
 * @since 2025/05/31
 */
@Data
public class ProductCategoryInfoDTO {

    /**
     * 商品信息分类名字
     */
    @NotNull(message = "商品信息分类名字不能为空")
    private Integer parentId;

    /**
     * 商品信息分类名字
     */
    @NotNull(message = "商品信息分类名字不能为空")
    private String name;

    /**
     * 排序值
     */
    @NotNull(message = "排序值")
    private Integer sortOrder;

    /**
     * 用户id
     */
    public Long userId;
}