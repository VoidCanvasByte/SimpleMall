package com.example.simple.mall.common.dto.product;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import java.util.Date;

@Data
public class ProductFavoritesItemsReDTO {

    /**
     * id
     */
    public Long id;

    /**
     * 所属收藏夹id
     */
    public Long favoritesId;

    /**
     * 商品变体id
     */
    public Long variantId;

    /**
     * 添加时间
     */
    public Date createTime;
}
