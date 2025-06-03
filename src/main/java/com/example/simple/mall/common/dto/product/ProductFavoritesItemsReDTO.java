package com.example.simple.mall.common.dto.product;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd-HH:mm:ss")
    public Date createTime;
}
