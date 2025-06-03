package com.example.simple.mall.api.mapStruct;

import com.example.simple.mall.common.dto.product.ProductReviewsInfoDTO;
import com.example.simple.mall.common.dto.product.ProductReviewsUpdateDTO;
import com.example.simple.mall.common.entity.ProductReviewsEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * ProductReviewsMapStruct
 *
 * @author sunny
 * @since 2025/06/03
 */
@Mapper
public interface ProductReviewsMapStruct {

    ProductReviewsMapStruct INSTANCE = Mappers.getMapper(ProductReviewsMapStruct.class);

    /**
     * productReviewsInfoDTOToEntity
     *
     * @param productReviewsInfoDTO productReviewsInfoDTO
     * @return @return {@code ProductReviewsEntity }
     * @author sunny
     * @since 2025/06/03
     */
    ProductReviewsEntity productReviewsInfoDTOToEntity(ProductReviewsInfoDTO productReviewsInfoDTO);

    /**
     * productReviewsUpdateDTOToEntity
     *
     * @param productReviewsUpdateDTO productReviewsUpdateDTO
     * @return @return {@code ProductReviewsEntity }
     * @author sunny
     * @since 2025/06/03
     */
    ProductReviewsEntity productReviewsUpdateDTOToEntity(ProductReviewsUpdateDTO productReviewsUpdateDTO);
}
