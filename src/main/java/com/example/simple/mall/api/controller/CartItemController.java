package com.example.simple.mall.api.controller;

import com.example.simple.mall.api.service.CartItemService;
import com.example.simple.mall.common.dto.CartItemDTO;
 import com.example.simple.mall.common.entity.CartItem;
import com.example.simple.mall.common.enu.ResponseEnum;
import com.example.simple.mall.common.response.ResponseResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 购物车控制层
 *
 * @author sunny
 * @since 2025/05/15
 */

@RestController
@RequestMapping("/apply/cart")
@Tag(name = "CartItemController", description = "购物车控制器")
public class CartItemController {

    @Autowired
    private CartItemService cartItemService;

    /**
     * 添加购物车
     *
     * @param cartItemDTO cartItemDTO
     * @author sunny
     * @since 2025/05/15
     */
    @PostMapping("/add")
    @Operation(summary = "添加购物车", description = "添加购物车")
    public ResponseResult<CartItemDTO> addToCart(@RequestBody CartItemDTO cartItemDTO) {
        cartItemService.addToCart(cartItemDTO);
        return ResponseResult.out(ResponseEnum.SUCCESS);
    }

    /**
     * 根据用户查看购物车内容
     *
     * @param userId 用户ID
     * @author sunny
     * @since 2025/05/15
     */
    @GetMapping("/list")
    @Operation(summary = "根据用户查看购物车内容", description = "根据用户查看购物车内容")
    public ResponseResult<List<CartItem>> list(@RequestAttribute Long userId) {
        List<CartItem> list = cartItemService.listByUserId(userId);
        return ResponseResult.out(ResponseEnum.SUCCESS, list);
    }

    /**
     * 单次添加或者减少购物车中物品的数量
     *
     * @param cartItemDTO cartItemDTO
     * @author sunny
     * @since 2025/05/15
     */
    @PostMapping("/update")
    @Operation(summary = "单次添加或者减少购物车中物品的数量", description = "单次添加或者减少购物车中物品的数量")
    public ResponseResult<CartItemDTO> updateCartItem(@RequestBody CartItemDTO cartItemDTO) {
        cartItemService.updateCartItem(cartItemDTO);
        return ResponseResult.out(ResponseEnum.SUCCESS);
    }

    /**
     * 自定义修改商品数量
     *
     * @param cartItemDTO cartItemDTO
     * @author sunny
     * @since 2025/05/15@return @return {@code ResponseResult<CartItemDTO> }
     */
    @DeleteMapping("/custom/update")
    @Operation(summary = "自定义修改商品数量", description = "自定义修改商品数量")
    public ResponseResult<CartItemDTO> customUpdateTag(@RequestBody CartItemDTO cartItemDTO) {
        cartItemService.customUpdateTag(cartItemDTO);
        return ResponseResult.out(ResponseEnum.SUCCESS);
    }

    /**
     * 清空购物车
     *
     * @param userId 用户ID
     * @author sunny
     * @since 2025/05/15
     */
    @DeleteMapping("/clear")
    @Operation(summary = "清空购物车", description = "清空购物车")
    public ResponseResult<CartItemDTO> clear(@RequestAttribute Long userId) {
        cartItemService.clearCart(userId);
        return ResponseResult.out(ResponseEnum.SUCCESS);
    }

}
