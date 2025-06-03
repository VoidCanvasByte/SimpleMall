package com.example.simple.mall.common.dto.product;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;


/**
 * 商品评价dto
 *
 * @author sunny
 * @since 2025/06/03
 */
@Data
public class ProductReviewsInfoDTO {

    /**
     * 评价商品id
     */
    @NotNull(message = "评价商品id不能为空")
    private Long productId;

    /**
     * 评价用户id
     */
    @NotNull(message = "评价用户id不能为空")
    private Long userId;

    /**
     * 评分（1–5)
     */
    @NotNull(message = "评分不能为空")
    private Integer rating;

    /**
     * 评价内容
     */
    private String comment;

    /**
     * 添加时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd-HH:mm:ss")
    private Date createTime;

    /**
     * 添加时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd-HH:mm:ss")
    private Date updateTime;
}
