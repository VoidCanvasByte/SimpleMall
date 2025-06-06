package com.example.simple.mall.api.controller;

import com.example.simple.mall.api.service.UserService;
import com.example.simple.mall.common.dto.LoginRequestDTO;
import com.example.simple.mall.common.dto.user.UserAddressesDTO;
import com.example.simple.mall.common.dto.user.UserDTO;
import com.example.simple.mall.common.dto.user.UserInfoDTO;
import com.example.simple.mall.common.dto.user.UserReturnInfoDTO;
import com.example.simple.mall.common.entity.UserEntity;
import com.example.simple.mall.common.enu.ResponseEnum;
import com.example.simple.mall.common.exception.response.ResponseResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 用户控制层
 *
 * @author sunny
 * @since 2025/05/05
 */
@RestController
@RequestMapping("/apply/user")
@Tag(name = "UserControllerAPI", description = "用户管理控制器接口")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 用户注册申请
     *
     * @author sunny
     * @since 2025/05/05
     */
    @Operation(summary = "用户注册申请", description = "用户信息注册")
    @PostMapping("/register")
    public ResponseResult<UserDTO> addUser(@Validated @RequestBody UserInfoDTO userInfoDTO) {
        userService.addUser(userInfoDTO);
        return ResponseResult.out(ResponseEnum.SUCCESS);
    }

    /**
     * 维护用户地址信息
     *
     * @param userAddressesDTO userAddressesDTO
     * @author sunny
     * @since 2025/05/31
     */
    @Operation(summary = "维护用户地址信息", description = "维护用户地址信息")
    @PutMapping("/update/info")
    public ResponseResult<UserAddressesDTO> userMaintainInfo(@Validated @RequestBody UserAddressesDTO userAddressesDTO) {
        userService.userMaintainInfo(userAddressesDTO);
        return ResponseResult.out(ResponseEnum.SUCCESS);
    }

    /**
     * 根据用户ID查询用户信息
     *
     * @param userId userId
     * @return @return {@code ResponseResult<List<UserReturnInfoDTO>> }
     * @author sunny
     * @since 2025/05/31
     */
    @Operation(summary = "根据用户ID查询用户信息", description = "根据用户ID查询用户信息")
    @GetMapping("/select/info/{userId}")
    public ResponseResult<UserReturnInfoDTO> selectUserInfo(@PathVariable Long userId) {
        UserReturnInfoDTO UserReturnInfo = userService.selectUserInfo(userId);
        return ResponseResult.out(ResponseEnum.SUCCESS, UserReturnInfo);
    }

    /**
     * 更新用户基础信息
     *
     * @return @return {@code ResponseResult<UserDTO> }
     * @author sunny
     * @since 2025/05/05
     */
    @Operation(summary = "更新用户基础信息", description = "更新用户基础信息")
    @PutMapping("/update")
    public ResponseResult<UserDTO> userUpdate(@RequestBody UserDTO userDto) {
        userService.updateUser(userDto);
        return ResponseResult.out(ResponseEnum.SUCCESS);
    }

    /**
     * 用户注销信息
     *
     * @return @return {@code ResponseResult<UserDTO> }
     * @author sunny
     * @since 2025/05/05
     */
    @Operation(summary = "用户注销信息", description = "用于用户信息的封存")
    @PutMapping("/delete/{userId}")
    public ResponseResult<UserDTO> userLogout(@PathVariable String userId) {
        userService.userLogout(userId);
        return ResponseResult.out(ResponseEnum.SUCCESS);
    }

    /**
     * 用户登陆
     *
     * @return @return {@code ResponseResult<UserDTO> }
     * @author sunny
     * @since 2025/05/05
     */
    @Operation(summary = "用户登陆")
    @PostMapping("/login")
    public ResponseResult<Map<String, UserEntity>> login(@Valid @RequestBody LoginRequestDTO loginRequest) {
        User user = userService.login(loginRequest.getEmail(), loginRequest.getPassword());
        Map<String, UserEntity> resultMap = userService.resultToken(user, loginRequest);
        return ResponseResult.out(ResponseEnum.SUCCESS, resultMap);
    }

}
