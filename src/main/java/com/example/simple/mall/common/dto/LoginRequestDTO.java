package com.example.simple.mall.common.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 登陆请求携带信息
 *
 * @author sunny
 * @since 2025/05/05
 */
@Data
public class LoginRequestDTO {

    /**
     * 用户邮箱
     */
    @Schema(description = "用户邮箱")
    @NotBlank(message = "用户邮箱不能为空")
    public String email;

    /**
     * 用户密码
     */
    @Schema(description = "用户密码")
    @NotBlank(message = "用户密码不能为空")
    public String password;
}
