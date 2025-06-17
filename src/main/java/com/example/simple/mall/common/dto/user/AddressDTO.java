package com.example.simple.mall.common.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * AddressDTO（用于自动识别地址）
 *
 * @author sunny
 * @since 2025/05/31
 */
@Data
public class AddressDTO {

    /**
     * 都道府県
     */
    @Schema(description = "都道府県")
    private String prefecture;

    /**
     * 市区町村
     */
    @Schema(description = "市区町村")
    private String city;

    /**
     * 町名
     */
    @Schema(description = "町名")
    private String town;
}