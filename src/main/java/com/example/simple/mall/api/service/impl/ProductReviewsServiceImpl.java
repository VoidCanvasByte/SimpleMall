package com.example.simple.mall.api.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.simple.mall.api.mapper.ProductReviewsMapper;
import com.example.simple.mall.api.service.ProductReviewsService;
import com.example.simple.mall.common.dto.product.ProductReviewsInfoDTO;
import com.example.simple.mall.common.dto.product.ProductReviewsUpdateDTO;
import com.example.simple.mall.common.entity.ProductReviewsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 商品评价服务实现类
 *
 * @author sunny
 * @since 2025-06-03
 */
@Service
public class ProductReviewsServiceImpl extends ServiceImpl<ProductReviewsMapper, ProductReviewsEntity> implements ProductReviewsService {


    @Autowired
    public ProductReviewsMapper productReviewsMapper;


    /**
     * 添加商品评论
     *
     * @param productReviewsInfoDTO productReviewsInfoDTO
     * @author sunny
     * @since 2025/06/03
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addProductReviews(ProductReviewsInfoDTO productReviewsInfoDTO) {

    }

    /**
     * 删除商品评价
     *
     * @param productReviewsId 商品评价表ID
     * @author sunny
     * @since 2025/06/03
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteProductReviews(Long productReviewsId) {

    }

    /**
     * 更新商品评价记录
     *
     * @param productReviewsUpdateDTO productReviewsUpdateDTO
     * @author sunny
     * @since 2025/06/03
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateProductReviews(ProductReviewsUpdateDTO productReviewsUpdateDTO) {

    }
}
