package com.example.simple.mall.common.dto;

import lombok.Data;

/**
 * 用户信息
 *
 * @author sunny
 * @since 2025/05/05
 */
@Data
public class UserDTO {
    /**
     * 用户ID
     */
    private Long id;

    /**
     * 用户昵称
     */
    private String userName;

    /**
     * 用户邮箱（唯一主键，一个邮箱只能申请一个用户）
     */
    private String email;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 性别：1男，2女，0未知
     */
    private Integer userGender;
}
