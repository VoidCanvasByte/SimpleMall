package com.example.simple.mall.api.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.simple.mall.common.dto.cart.CartItemAddDTO;
import com.example.simple.mall.common.dto.cart.CartItemUpdateDTO;
import com.example.simple.mall.common.dto.cart.CartUpdateDTO;
import com.example.simple.mall.common.entity.ShoppingCartItemEntity;

import java.util.List;


/**
 * ShoppingCartItemService
 *
 * @author sunny
 * @since 2025/05/15
 */
public interface ShoppingCartItemService extends IService<ShoppingCartItemEntity> {

    /**
     * 添加购物车以及物品
     *
     * @param cartItemAddDTO cartItemAddDTO
     * @author sunny
     * @since 2025/05/15
     */
    void addToCart(CartItemAddDTO cartItemAddDTO);

    /**
     * 根据用户查询购物车商品信息
     *
     * @param userId 用户ID
     * @return @return {@code List<CartItemAddDTO> }
     * @author sunny
     * @since 2025/05/15
     */
    List<ShoppingCartItemEntity> listByUserId(Long userId);

    /**
     * 单次添加或者减少购物车中物品的数量
     *
     * @param cartUpdateDTO cartItemDTO
     * @author sunny
     * @since 2025/05/15
     */
    void updateCartItem(CartUpdateDTO cartUpdateDTO);

    /**
     * 一键清空购物车
     *
     * @param cartId 购物车ID
     * @author sunny
     * @since 2025/05/15
     */
    void clearCart(Long cartId);

    /**
     * 自定义修改商品数量
     *
     * @param cartItemAddDTO cartItemAddDTO
     * @author sunny
     * @since 2025/05/15@return@return
     */
    void customUpdateTag(CartItemUpdateDTO cartItemAddDTO);
}
