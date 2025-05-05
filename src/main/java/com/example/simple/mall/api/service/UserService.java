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

    /**
     * 用户注销
     *
     * @param userId 用户ID
     * @author sunny
     * @since 2025/05/05@return@return
     */
    void userLogout(String userId);

    /**
     * 用户登陆
     *
     * @param email    邮箱
     * @param password 密码
     * @return @return {@code UserDTO }
     * @author sunny
     * @since 2025/05/05
     */
    UserDTO login(String email, String password);
}
