package com.example.simple.mall.api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.simple.mall.common.entity.CartItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;


/**
 * CartItemMapper
 *
 * @author sunny
 * @since 2025/05/15
 */
@Mapper
public interface CartItemMapper extends BaseMapper<CartItem> {

    /**
     * 根据ID更新购物车中的数量
     *
     * @param quantityOld 数量
     * @param id          id
     * @author sunny
     * @since 2025/05/15
     */
    void updateQuantity(@Param("quantity") Integer quantityOld,
                        @Param("id") Integer id);

    /**
     * 根据订单信息修改购物车信息
     *
     * @param userId    用户id
     * @param productId 产品ID
     * @param quantity  数量
     */
    void clearCartItemProduct(@Param("userId") String userId,
                              @Param("productId") String productId,
                              @Param("quantity") BigDecimal quantity);
}