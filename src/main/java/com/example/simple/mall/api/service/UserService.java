package com.example.simple.mall.api.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.simple.mall.common.dto.LoginRequestDTO;
import com.example.simple.mall.common.dto.user.UserAddressesDTO;
import com.example.simple.mall.common.dto.user.UserDTO;
import com.example.simple.mall.common.dto.user.UserInfoDTO;
import com.example.simple.mall.common.dto.user.UserReturnInfoDTO;
import com.example.simple.mall.common.entity.UserEntity;
import org.springframework.security.core.userdetails.User;

import java.util.List;
import java.util.Map;

/**
 * 用户Service
 *
 * @author sunny
 * @since 2025/05/05
 */
public interface UserService extends IService<UserEntity> {

    /**
     * 用户添加申请
     *
     * @author sunny
     * @since 2025/05/05
     */
    void addUser(UserInfoDTO userInfoDTO);

    /**
     * 判断用户是否存在（用户不存在返回true）
     *
     * @param userId 用户ID
     * @return @return {@code Boolean }
     * @author sunny
     * @since 2025/05/24
     */
    Boolean judgeUserIfNull(Long userId);

    /**
     * 更新用户基础信息
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
    User login(String email, String password);

    /**
     * 获取用户的token
     *
     * @param user         user
     * @param loginRequest loginRequest
     * @return @return {@code Map<String, User> }
     * @author sunny
     * @since 2025/05/16
     */
    Map<String, UserEntity> resultToken(User user, LoginRequestDTO loginRequest);


    /**
     * 维护用户地址信息
     *
     * @param userAddressesDTO userAddressesDTO
     * @author sunny
     * @since 2025/05/31
     */
    void userMaintainInfo(UserAddressesDTO userAddressesDTO);

    /**
     * 根据用户ID查询用户信息
     *
     * @param userId userId
     * @return @return {@code List<UserReturnInfoDTO> }
     * @author sunny
     * @since 2025/05/31
     */
    UserReturnInfoDTO selectUserInfo(Long userId);
}
