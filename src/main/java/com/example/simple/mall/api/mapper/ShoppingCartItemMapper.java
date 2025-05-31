package com.example.simple.mall.api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.simple.mall.common.entity.ShoppingCartItemEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;


/**
 * ShoppingCartItemMapper
 *
 * @author sunny
 * @since 2025/05/15
 */
@Mapper
public interface ShoppingCartItemMapper extends BaseMapper<ShoppingCartItemEntity> {

    /**
     * 根据ID更新购物车中的数量
     *
     * @param quantity 数量
     * @author sunny
     * @since 2025/05/15@return
     */
    void updateQuantity(@Param("quantity") Integer quantity,
                        @Param("id") Long id);

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