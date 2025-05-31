package com.example.simple.mall.api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.simple.mall.api.mapStruct.ProductCategoryMapperStruct;
import com.example.simple.mall.api.mapper.ProductCategoryMapper;
import com.example.simple.mall.api.service.ProductCategoryService;
import com.example.simple.mall.common.dto.product.ProductCategoryInfoDTO;
import com.example.simple.mall.common.dto.product.ProductCategoryReturnDTO;
import com.example.simple.mall.common.dto.product.ProductUpdateDTO;
import com.example.simple.mall.common.dto.user.UserBaseDTO;
import com.example.simple.mall.common.entity.ProductCategoryEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author sunny
 * @since 2025/05/09
 */
@Service
public class ProductCategoryServiceImpl extends ServiceImpl<ProductCategoryMapper, ProductCategoryEntity> implements ProductCategoryService {

    @Autowired
    private ProductCategoryMapper productCategoryMapper;

    /**
     * 添加商品分类
     *
     * @param productCategoryInfoDTO productCategoryInfoDTO
     * @author sunny
     * @since 2025/05/25
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addCategory(ProductCategoryInfoDTO productCategoryInfoDTO) {
        ProductCategoryEntity productCategoryEntity = new ProductCategoryEntity();
        BeanUtils.copyProperties(productCategoryInfoDTO, productCategoryEntity);
        productCategoryMapper.insert(productCategoryEntity);
    }

    /**
     * 更新商品分类信息
     *
     * @param productUpdateDTO productUpdateDTO
     * @author sunny
     * @since 2025/05/25
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateCategory(ProductUpdateDTO productUpdateDTO) {
        ProductCategoryEntity productCategoryEntity = new ProductCategoryEntity();
        QueryWrapper<ProductCategoryEntity> productCategoryWrapper = new QueryWrapper<>();
        productCategoryWrapper.eq("id", productUpdateDTO.getId());
        productCategoryWrapper.eq("name", productUpdateDTO.getName());
        BeanUtils.copyProperties(productUpdateDTO, productCategoryEntity);
        productCategoryMapper.update(productCategoryEntity, productCategoryWrapper);
    }

    /**
     * 查询当前用户下全部商品分类列表
     *
     * @param userBaseDTO userBaseDTO
     * @return {@code List<ProductCategoryReturnDTO> }
     * @author sunny
     * @since 2025/05/31@return
     */
    @Override
    public List<ProductCategoryReturnDTO> listAll(UserBaseDTO userBaseDTO) {
        QueryWrapper<ProductCategoryEntity> productCategoryWrapper = new QueryWrapper<>();
        productCategoryWrapper.eq("user_id", userBaseDTO.getUserId());
        List<ProductCategoryEntity> productCategoryEntities = productCategoryMapper.selectList(productCategoryWrapper);
        return ProductCategoryMapperStruct.INSTANCE.productCategoryEntitiesToDTO(productCategoryEntities);
    }
}
