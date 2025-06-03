package com.example.simple.mall.api.controller;

import com.example.simple.mall.api.service.ProductFavoritesItemsService;
import com.example.simple.mall.common.annotation.UserVerification;
import com.example.simple.mall.common.dto.product.ProductFavoritesItemsInfoDTO;
import com.example.simple.mall.common.entity.ProductFavoritesItemsEntity;
import com.example.simple.mall.common.enu.ResponseEnum;
import com.example.simple.mall.common.response.ResponseResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ProductFavoritesItemsController
 *
 * @author sunny
 * @since 2025/06/02
 */
@RestController
@RequestMapping("/apply/product/favorites")
@Tag(name = "ProductFavoritesItemsControllerAPI", description = "商品收藏夹控制层")
public class ProductFavoritesItemsController {

    @Autowired
    public ProductFavoritesItemsService productFavoritesItemsService;

    /**
     * 添加到收藏夹
     *
     * @param productFavoritesItemsInfoDTO productFavoritesItemsInfoDTO
     * @return @return {@code ResponseResult<ProductAddInfoDTO> }
     * @author sunny
     * @since 2025/05/08
     */
    @UserVerification
    @PostMapping(value = "/add")
    @Operation(summary = "添加到收藏夹", description = "添加到收藏夹")
    public ResponseResult<ProductFavoritesItemsEntity> addProductFavorites(@Validated @RequestBody ProductFavoritesItemsInfoDTO productFavoritesItemsInfoDTO) {
        productFavoritesItemsService.addProductFavorites(productFavoritesItemsInfoDTO);
        return ResponseResult.out(ResponseEnum.SUCCESS);
    }

}
