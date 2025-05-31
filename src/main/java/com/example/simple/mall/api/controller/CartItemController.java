package com.example.simple.mall.api.controller;

import com.example.simple.mall.api.service.ShoppingCartItemService;
import com.example.simple.mall.common.dto.cart.CartItemAddDTO;
import com.example.simple.mall.common.dto.cart.CartItemUpdateDTO;
import com.example.simple.mall.common.dto.cart.CartUpdateDTO;
import com.example.simple.mall.common.entity.ShoppingCartItemEntity;
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
    private ShoppingCartItemService shoppingCartItemService;

    /**
     * 添加购物车以及物品
     *
     * @param cartItemAddDTO cartItemAddDTO
     * @author sunny
     * @since 2025/05/15
     */
    @PostMapping("/add")
    @Operation(summary = "添加购物车以及物品", description = "添加购物车以及物品")
    public ResponseResult<CartItemAddDTO> addToCart(@RequestBody CartItemAddDTO cartItemAddDTO) {
        shoppingCartItemService.addToCart(cartItemAddDTO);
        return ResponseResult.out(ResponseEnum.SUCCESS);
    }

    /**
     * 根据用户查看购物车内容
     *
     * @param userId 用户ID
     * @author sunny
     * @since 2025/05/15
     */
    @GetMapping("/list/{userId}")
    @Operation(summary = "根据用户查看购物车内容", description = "根据用户查看购物车内容")
    public ResponseResult<List<ShoppingCartItemEntity>> listAll(@PathVariable Long userId) {
        List<ShoppingCartItemEntity> listAll = shoppingCartItemService.listByUserId(userId);
        return ResponseResult.out(ResponseEnum.SUCCESS, listAll);
    }

    /**
     * 单次添加或者减少购物车中物品的数量
     *
     * @param cartUpdateDTO cartItemDTO
     * @author sunny
     * @since 2025/05/15
     */
    @PostMapping("/update")
    @Operation(summary = "单次添加或者减少购物车中物品的数量", description = "单次添加或者减少购物车中物品的数量")
    public ResponseResult<CartItemAddDTO> updateCartItem(@RequestBody CartUpdateDTO cartUpdateDTO) {
        shoppingCartItemService.updateCartItem(cartUpdateDTO);
        return ResponseResult.out(ResponseEnum.SUCCESS);
    }

    /**
     * 自定义修改商品数量
     *
     * @param cartItemAddDTO cartItemAddDTO
     * @author sunny
     * @since 2025/05/15@return @return {@code ResponseResult<CartItemAddDTO> }
     */
    @DeleteMapping("/custom/update")
    @Operation(summary = "自定义修改商品数量", description = "自定义修改商品数量")
    public ResponseResult<CartItemAddDTO> customUpdateTag(@RequestBody CartItemUpdateDTO cartItemAddDTO) {
        shoppingCartItemService.customUpdateTag(cartItemAddDTO);
        return ResponseResult.out(ResponseEnum.SUCCESS);
    }

    /**
     * 清空购物车
     *
     * @param cartId 购物车ID
     * @author sunny
     * @since 2025/05/15
     */
    @DeleteMapping("/clear/{cartId}")
    @Operation(summary = "清空购物车", description = "清空购物车")
    public ResponseResult<CartItemAddDTO> clearAll(@PathVariable Long cartId) {
        shoppingCartItemService.clearCart(cartId);
        return ResponseResult.out(ResponseEnum.SUCCESS);
    }

}
