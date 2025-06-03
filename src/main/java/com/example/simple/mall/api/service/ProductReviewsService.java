package com.example.simple.mall.api.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.simple.mall.common.dto.product.ProductReviewsInfoDTO;
import com.example.simple.mall.common.dto.product.ProductReviewsReDTO;
import com.example.simple.mall.common.dto.product.ProductReviewsUpdateDTO;
import com.example.simple.mall.common.entity.ProductReviewsEntity;

import java.util.List;

/**
 * <p>
 * 商品评价表 服务类
 * </p>
 *
 * @author sunny
 * @since 2025-06-03
 */
public interface ProductReviewsService extends IService<ProductReviewsEntity> {

    /**
     * 添加商品评论
     *
     * @param productReviewsInfoDTO productReviewsInfoDTO
     * @author sunny
     * @since 2025/06/03
     */
    void addProductReviews(ProductReviewsInfoDTO productReviewsInfoDTO);

    /**
     * 删除商品评价
     *
     * @param productReviewsId 商品评价表ID
     * @author sunny
     * @since 2025/06/03
     */
    void deleteProductReviews(Long productReviewsId);

    /**
     * 更新商品评价记录
     *
     * @param productReviewsUpdateDTO productReviewsUpdateDTO
     * @author sunny
     * @since 2025/06/03
     */
    void updateProductReviews(ProductReviewsUpdateDTO productReviewsUpdateDTO);

    /**
     * 查询商品评价
     *
     * @param productId 商品ID
     * @author sunny
     * @since 2025/06/03@return
     */
    List<ProductReviewsReDTO> selectProductReviews(Long productId);

    /**
     * 根据商品ID查询对应的商品评论
     *
     * @param productId 商品ID
     * @return @return {@code List<ProductReviewsReDTO> }
     * @author sunny
     * @since 2025/06/03
     */
    List<ProductReviewsReDTO> listAll(Long productId);
}