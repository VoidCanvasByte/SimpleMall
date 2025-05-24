package com.example.simple.mall.api.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.simple.mall.common.dto.CartItemDTO;
import com.example.simple.mall.common.entity.CartItem;

import java.util.List;


/**
 * CartItemService
 *
 * @author sunny
 * @since 2025/05/15
 */
public interface CartItemService extends IService<CartItem> {

    /**
     * 添加购物车
     *
     * @param cartItemDTO cartItemDTO
     * @author sunny
     * @since 2025/05/15
     */
    void addToCart(CartItemDTO cartItemDTO);

    /**
     * 根据用户查询购物车商品信息
     *
     * @param userId 用户ID
     * @return @return {@code List<CartItemDTO> }
     * @author sunny
     * @since 2025/05/15
     */
    List<CartItem> listByUserId(Long userId);

    /**
     * 单次添加或者减少购物车中物品的数量
     *
     * @param cartItemDTO cartItemDTO
     * @author sunny
     * @since 2025/05/15
     */
    void updateCartItem(CartItemDTO cartItemDTO);

    /**
     * 一键清空购物车
     *
     * @param userId 用户ID
     * @author sunny
     * @since 2025/05/15
     */
    void clearCart(Long userId);

    /**
     * 自定义修改商品数量
     *
     * @param cartItemDTO cartItemDTO
     * @author sunny
     * @since 2025/05/15@return
     */
    void customUpdateTag(CartItemDTO cartItemDTO);
}
