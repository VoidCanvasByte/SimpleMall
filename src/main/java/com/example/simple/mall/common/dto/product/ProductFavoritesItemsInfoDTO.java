package com.example.simple.mall.common.dto.product;

import com.example.simple.mall.common.dto.user.UserBaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 收藏夹添加DTO
 *
 * @author sunny
 * @since 2025/06/02
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ProductFavoritesItemsInfoDTO extends UserBaseDTO {

    /**
     * 商品变体id
     */
    public Long variantId;
}
