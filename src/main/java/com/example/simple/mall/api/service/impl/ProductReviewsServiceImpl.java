package com.example.simple.mall.api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.simple.mall.api.mapStruct.ProductReviewsMapStruct;
import com.example.simple.mall.api.mapper.ProductReviewsMapper;
import com.example.simple.mall.api.service.ProductMainService;
import com.example.simple.mall.api.service.ProductReviewsService;
import com.example.simple.mall.common.dto.product.ProductReviewsInfoDTO;
import com.example.simple.mall.common.dto.product.ProductReviewsReDTO;
import com.example.simple.mall.common.dto.product.ProductReviewsUpdateDTO;
import com.example.simple.mall.common.entity.ProductEntity;
import com.example.simple.mall.common.entity.ProductReviewsEntity;
import com.example.simple.mall.common.enu.ResponseEnum;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    @Autowired
    public ProductMainService productMainService;


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
        QueryWrapper<ProductEntity> productEntityQueryWrapper = new QueryWrapper<>();
        productEntityQueryWrapper.eq("id", productReviewsInfoDTO.getProductId());
        ProductEntity productEntityOld = productMainService.getOne(productEntityQueryWrapper);
        if (ObjectUtils.isEmpty(productEntityOld)) {
            throw new RuntimeException(ResponseEnum.PRODUCT_NOT_EXIST.getMessage());
        }
        ProductReviewsEntity productReviewsEntity = ProductReviewsMapStruct.INSTANCE.productReviewsInfoDTOToEntity(productReviewsInfoDTO);
        productReviewsMapper.insert(productReviewsEntity);
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
        productReviewsMapper.deleteById(productReviewsId);
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
        QueryWrapper<ProductEntity> productEntityQueryWrapper = new QueryWrapper<>();
        productEntityQueryWrapper.eq("id", productReviewsUpdateDTO.getProductId());
        ProductEntity productEntityOld = productMainService.getOne(productEntityQueryWrapper);
        if (ObjectUtils.isEmpty(productEntityOld)) {
            throw new RuntimeException(ResponseEnum.PRODUCT_NOT_EXIST.getMessage());
        }
        QueryWrapper<ProductReviewsEntity> productReviewsEntityQueryWrapper = new QueryWrapper<>();
        productReviewsEntityQueryWrapper.eq("id", productReviewsUpdateDTO.getId());
        ProductReviewsEntity productReviewsEntityOld = this.getOne(productReviewsEntityQueryWrapper);

        ProductReviewsEntity productReviewsEntity = ProductReviewsMapStruct.INSTANCE.productReviewsUpdateDTOToEntity(productReviewsUpdateDTO);
        if (!ObjectUtils.isEmpty(productReviewsEntityOld)) {
            this.updateById(productReviewsEntity);
        } else {
            productReviewsMapper.insert(productReviewsEntity);
        }
    }

    /**
     * 查询商品评价
     *
     * @param productId 商品ID
     * @return @return {@code List<ProductReviewsReDTO> }
     * @author sunny
     * @since 2025/06/03
     */
    @Override
    public List<ProductReviewsReDTO> selectProductReviews(Long productId) {
        return this.listAll(productId);
    }

    /**
     * 根据商品ID查询对应的商品评论
     *
     * @param productId 商品ID
     * @return @return {@code List<ProductReviewsReDTO> }
     * @author sunny
     * @since 2025/06/03
     */
    @Override
    public List<ProductReviewsReDTO> listAll(Long productId) {
        return productReviewsMapper.listAll(productId);
    }
}
