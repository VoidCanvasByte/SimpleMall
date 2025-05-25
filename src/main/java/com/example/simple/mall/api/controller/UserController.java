package com.example.simple.mall.api.controller;

import com.example.simple.mall.api.service.UserService;
import com.example.simple.mall.common.dto.LoginRequestDTO;
import com.example.simple.mall.common.dto.user.UserDTO;
import com.example.simple.mall.common.enu.ResponseEnum;
import com.example.simple.mall.common.response.ResponseResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
     * @since 2025/05/05@return@return {@code ResponseResult,ResponseResult }
     */
    @Operation(summary = "用户注册申请", description = "用户信息注册")
    @PostMapping("/register")
    public ResponseResult<UserDTO> addUser(@Validated @RequestBody UserDTO userDto) {
        userService.addUser(userDto);
        return ResponseResult.out(ResponseEnum.SUCCESS);
    }

//    @PreAuthorize("hasRole('ADMIN')")//指定方法访问控制表达式的注释将进行评估以决定是否允许使用方法调用

    /**
     * 更新用户信息
     *
     * @return @return {@code ResponseResult<UserDTO> }
     * @author sunny
     * @since 2025/05/05
     */
    @Operation(summary = "更新用户信息", description = "用户用户信息的更新")
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
    public ResponseResult<Map<String, User>> login(@RequestBody LoginRequestDTO loginRequest) {
        User user = userService.login(loginRequest.getEmail(), loginRequest.getPassword());
        Map<String, User> resultMap = userService.resultToken(user, loginRequest);
        return ResponseResult.out(ResponseEnum.SUCCESS, resultMap);
    }

}
