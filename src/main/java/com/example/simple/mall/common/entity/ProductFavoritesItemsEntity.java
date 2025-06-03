package com.example.simple.mall.common.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * 收藏夹明细表
 *
 * @author sunny
 * @since 2025/06/02
 */
@Data
@TableName("product_favorites_items")
public class ProductFavoritesItemsEntity {

    /**
     * id
     */
    @TableField("id")
    public Long id;

    /**
     * 所属收藏夹id
     */
    @TableField("favorites_id")
    public Long favoritesId;

    /**
     * 商品变体id
     */
    @TableField("variant_id")
    public Long variantId;

    /**
     * 添加时间
     */
    @TableField("create_time")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd-HH:mm:ss")
    public String createTime;
}
