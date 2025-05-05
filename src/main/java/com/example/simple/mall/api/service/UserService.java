package com.example.simple.mall.api.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.simple.mall.common.dto.UserDTO;
import com.example.simple.mall.common.entity.User;

/**
 * 用户Service
 *
 * @author sunny
 * @since 2025/05/05
 */
public interface UserService extends IService<User> {

    /**
     * 用户添加申请
     *
     * @author sunny
     * @since 2025/05/05
     */
    void addUser(UserDTO userDto);

    /**
     * 更新用户信息
     *
     * @author sunny
     * @since 2025/05/05
     */
    void updateUser(UserDTO userDto);
}
