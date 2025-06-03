package com.example.simple.mall.api.mapper;

import com.example.simple.mall.common.dto.product.ProductReviewsReDTO;
import com.example.simple.mall.common.entity.ProductReviewsEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 商品评价表 Mapper 接口
 * </p>
 *
 * @author sunny
 * @since 2025-06-03
 */
public interface ProductReviewsMapper extends BaseMapper<ProductReviewsEntity> {

    /**
     * 根据商品ID查询对应的商品评论
     *
     * @param productId 商品ID
     * @return @return {@code List<ProductReviewsReDTO> }
     * @author sunny
     * @since 2025/06/03
     */
    List<ProductReviewsReDTO> listAll(@Param("productId") Long productId);
}