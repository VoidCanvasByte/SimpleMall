package com.example.simple.mall.common.dto;

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
    public String email;

    /**
     * 用户密码
     */
    public String password;
}
