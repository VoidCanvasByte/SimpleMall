package com.example.simple.mall.common.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


/**
 * 用户注册DTO
 *
 * @author sunny
 * @since 2025/06/06
 */
@Data
public class UserInfoDTO {

    /**
     * 用户昵称
     */
    @Schema(description = "用户昵称")
    private String userName;

    /**
     * 用户邮箱（唯一主键，一个邮箱只能申请一个用户）
     */
    @Schema(description = "用户邮箱（唯一主键，一个邮箱只能申请一个用户）")
    @NotNull(message = "用户邮箱不能为空")
    private String email;

    /**
     * 用户密码
     */
    @Schema(description = "用户密码")
    @NotNull(message = "用户密码不能为空")
    private String password;

    /**
     * 电话号码
     */
    @Schema(description = "电话号码")
    private String phone;

    /**
     * 性别：1男，2女，0未知
     */
    @Schema(description = " 性别：1男，2女，0未知")
    private Integer userGender;
}
