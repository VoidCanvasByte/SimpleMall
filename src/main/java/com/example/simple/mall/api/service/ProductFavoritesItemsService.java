package com.example.simple.mall.api.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.simple.mall.common.dto.favorites.ProductFavoritesDTO;
import com.example.simple.mall.common.dto.product.ProductFavoritesItemsInfoDTO;
import com.example.simple.mall.common.dto.user.UserBaseDTO;
import com.example.simple.mall.common.entity.ProductFavoritesItemsEntity;

import java.util.List;


/**
 * ProductFavoritesItemsService
 *
 * @author sunny
 * @since 2025/06/02
 */
public interface ProductFavoritesItemsService extends IService<ProductFavoritesItemsEntity> {

    /**
     * 添加到收藏夹
     *
     * @param productFavoritesItemsInfoDTO productFavoritesItemsInfoDTO
     * @since 2025/06/02
     */
    void addProductFavorites(ProductFavoritesItemsInfoDTO productFavoritesItemsInfoDTO);

    /**
     * 获取收藏夹全部信息
     *
     * @param userBaseDTO userBaseDTO
     * @author sunny
     * @since 2025/06/03@return @return {@code List<ProductFavoritesDTO> }
     */
    ProductFavoritesDTO getAllProductFavorites(UserBaseDTO userBaseDTO);
}
