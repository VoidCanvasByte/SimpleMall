package com.example.simple.mall.common.dto.product;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;


/**
 * 商品评价返回信息查询
 *
 * @author sunny
 * @since 2025/06/03
 */
@Data
public class ProductReviewsReDTO {

    /**
     * id
     */
    @Schema(description = "id")
    private Long id;

    /**
     * 评价商品id
     */
    @Schema(description = "评价商品id")
    private Long productId;

    /**
     * 评分（1–5)
     */
    @Schema(description = "评分（1–5)")
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
