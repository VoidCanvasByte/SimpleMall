package com.example.simple.mall.api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.simple.mall.common.entity.ProductDetails;
import org.apache.ibatis.annotations.Param;

/**
 * 商品详细信息表
 *
 * @author sunny
 * @since 2025/05/08
 */
public interface ProductDetailsMapper extends BaseMapper<ProductDetails> {


    /**
     * 扣除库存
     *
     * @param productId productId
     * @param quantity  quantity
     * @return @return int 影响行数
     * @author sunny
     * @since 2025/05/30
     */
    int updateQuantity(@Param("productId") Integer productId,
                       @Param("quantity") Integer quantity,
                       @Param("oldVersion")Integer oldVersion);
}
