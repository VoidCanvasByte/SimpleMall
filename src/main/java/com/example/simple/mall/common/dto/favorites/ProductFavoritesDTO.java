package com.example.simple.mall.common.dto.favorites;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 收藏夹返回信息
 *
 * @author sunny
 * @since 2025/06/03
 */
@Data
public class ProductFavoritesDTO {

    /**
     * id
     */
    public Long id;

    /**
     * 用户ID
     */
    public Long userId;


    /**
     * 收藏夹详情
     */
   public  List<ProductFavoritesItemDTO> productFavoritesItemDTOList = new ArrayList<>();


    /**
     * 收藏夹详情
     *
     * @author sunny
     * @since 2025/06/03
     */
    @Data
    public static class ProductFavoritesItemDTO {
        /**
         * 添加时间
         */
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd-HH:mm:ss")
        public Date createTime;

        /**
         * 所属收藏夹id
         */
        public Long favoritesId;

        /**
         * 商品变体id
         */
        public Long variantId;
    }
}
