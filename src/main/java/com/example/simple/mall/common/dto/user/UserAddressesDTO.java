package com.example.simple.mall.common.dto.user;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 用户info信息DTO
 *
 * @author sunny
 * @since 2025/05/31
 */
@Data
public class UserAddressesDTO {

    /**
     * 主表ID
     */
    public Long id;

    /**
     * 用户ID
     */
    @NotNull(message = "用户ID不能为空")
    public Long userId;

    /**
     * 收件人姓名
     */
    @NotNull(message = "收件人姓名不能为空")
    public String receiverName;

    /**
     * 收件人手机号
     */
    @NotNull(message = "收件人手机号不能为空")
    public String phone;

    /**
     * 详细地址
     */
    @NotNull(message = "详细地址不能为")
    public String streetAddress;

    /**
     * 郵便番号
     */
    @NotNull(message = "郵便番号不能为空")
    public String postalCode;

    /**
     * 是否默认地址（1:是；2:不是）
     */
    public Integer isDefault;
}