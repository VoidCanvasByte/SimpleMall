package com.example.simple.mall.api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.simple.mall.common.entity.ProductDetailsEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 商品详细信息表
 *
 * @author sunny
 * @since 2025/05/08
 */
@Mapper
public interface ProductDetailsMapper extends BaseMapper<ProductDetailsEntity> {


    /**
     * 扣除库存
     *
     * @param productId productId
     * @param quantity  quantity
     * @return @return int 影响行数
     * @author sunny
     * @since 2025/05/30
     */
    int updateQuantity(@Param("productId") Long productId,
                       @Param("quantity") Integer quantity,
                       @Param("oldVersion")Integer oldVersion);
}
