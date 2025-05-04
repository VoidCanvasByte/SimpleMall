package com.example.simple.mall.common.dto;

import lombok.Data;

/**
 * 基础DTO
 *
 * @author sunny
 * @since 2025/05/05
 */
@Data
public class BaseDTO {

    /**
     * 用户ID
     */
    private Long id;

    /**
     * 创建时间
     */
    private String creatTime;

    /**
     * 更新时间
     */
    private String updateTime;
}
