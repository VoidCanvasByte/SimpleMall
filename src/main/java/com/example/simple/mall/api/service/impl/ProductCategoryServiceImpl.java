package com.example.simple.mall.api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.simple.mall.api.mapStruct.ProductCategoryMapperStruct;
import com.example.simple.mall.api.mapper.ProductCategoryMapper;
import com.example.simple.mall.api.service.ProductCategoryService;
import com.example.simple.mall.common.dto.product.ProductCategoryInfoDTO;
import com.example.simple.mall.common.dto.product.ProductCategoryReturnDTO;
import com.example.simple.mall.common.dto.product.ProductCategoryUpdateDTO;
import com.example.simple.mall.common.dto.user.UserBaseDTO;
import com.example.simple.mall.common.entity.ProductCategoryEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
     * @param productCategoryUpdateDTO productCategoryUpdateDTO
     * @author sunny
     * @since 2025/05/25
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateCategory(ProductCategoryUpdateDTO productCategoryUpdateDTO) {
        ProductCategoryEntity productCategoryEntity = new ProductCategoryEntity();
        QueryWrapper<ProductCategoryEntity> productCategoryWrapper = new QueryWrapper<>();
        productCategoryWrapper.eq("id", productCategoryUpdateDTO.getId());
        productCategoryWrapper.eq("name", productCategoryUpdateDTO.getName());
        productCategoryWrapper.eq("user_id", productCategoryUpdateDTO.getUserId());
        BeanUtils.copyProperties(productCategoryUpdateDTO, productCategoryEntity);
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
        return buildCategoryTree(productCategoryEntities);
    }


    /**
     * 构建数据
     *
     * @param entityList entityList
     * @return {@code List<ProductCategoryReturnDTO> }
     * @author sunny
     * @since 2025/06/06
     */
    public List<ProductCategoryReturnDTO> buildCategoryTree(List<ProductCategoryEntity> entityList) {
        List<ProductCategoryReturnDTO> dtoList = entityList.stream()
                .map(ProductCategoryMapperStruct.INSTANCE::productCategoryEntitiesToDTO)
                .toList();

        Map<Long, ProductCategoryReturnDTO> idMap = new HashMap<>();
        for (ProductCategoryReturnDTO dto : dtoList) {
            idMap.put(dto.getId(), dto);
        }

        List<ProductCategoryReturnDTO> rootList = new ArrayList<>();
        for (ProductCategoryReturnDTO dto : dtoList) {
            long parentId = dto.getParentId() != null ? dto.getParentId() : 0L;
            if (parentId == 0L) {
                rootList.add(dto);
            } else {
                ProductCategoryReturnDTO parent = idMap.get(parentId);
                if (parent != null) {
                    parent.getChildren().add(dto);
                }
            }
        }
        return rootList;
    }
}
