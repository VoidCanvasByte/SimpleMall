package com.example.simple.mall.common.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 用于用户的切面判断
 *
 * @author sunny
 * @since 2025/05/25
 */
@Data
public class UserBaseDTO {

    /**
     * 用户id
     */
    @Schema(description = "用户id")
    public Long userId;
}

