package com.example.simple.mall.common.dto.product;

import com.example.simple.mall.common.dto.user.UserBaseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * ProductCategoryInfoDTO
 *
 * @author sunny
 * @since 2025/05/31
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ProductCategoryInfoDTO extends UserBaseDTO {

    /**
     * 商品信息分类名字
     */
    @Schema(description = "商品信息分类名字")
    @NotNull(message = "商品信息分类名字不能为空")
    private Long parentId;

    /**
     * 商品信息分类名字
     */
    @Schema(description = "商品信息分类名字")
    @NotNull(message = "商品信息分类名字不能为空")
    private String name;

    /**
     * 排序值
     */
    @Schema(description = "排序值")
    @NotNull(message = "排序值")
    private Integer sortOrder;
}