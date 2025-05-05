package com.example.simple.mall.common.dto;

import javax.validation.constraints.NotNull;

import com.example.simple.mall.common.entity.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户信息
 *
 * @author sunny
 * @since 2025/05/05
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UserDTO extends User{

    /**
     * 用户昵称
     */
    private String userName;

    /**
     * 用户邮箱（唯一主键，一个邮箱只能申请一个用户）
     */
    @NotNull(message = "用户邮箱不能为空")
    private String email;

    /**
     * 用户密码
     */
    @NotNull(message = "用户邮箱不能为空")
    private String password;

    /**
     * 性别：1男，2女，0未知
     */
    private Integer userGender;

    /**
     * 用户状态：0正常，1封禁
     */
    private Integer status;
}
