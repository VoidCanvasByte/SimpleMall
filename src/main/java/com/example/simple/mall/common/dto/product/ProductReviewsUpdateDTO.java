package com.example.simple.mall.common.dto.product;

import com.example.simple.mall.common.dto.user.UserBaseDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

/**
 * 用户评价更新DTO
 *
 * @author sunny
 * @since 2025/06/03
 */
@Data
public class ProductReviewsUpdateDTO extends UserBaseDTO {

    /**
     * 主键ID
     */
    @Schema(description = "主键ID")
    @NotNull(message = "主键ID不能为空")
    public Long id;

    /**
     * 评价商品id
     */
    @Schema(description = "评价商品id")
    @NotNull(message = "评价商品id不能为空")
    private Long productId;

    /**
     * 评分（1–5)
     */
    @Schema(description = "评分（1–5)")
    @NotNull(message = "评分不能为空")
    private Integer rating;

    /**
     * 评价内容
     */
    @Schema(description = "评价内容")
    private String comment;

    /**
     * 添加时间
     */
    @Schema(description = "添加时间")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd-HH:mm:ss")
    private Date createTime;

    /**
     * 更新时间
     */
    @Schema(description = "更新时间")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd-HH:mm:ss")
    private Date updateTime;
}
