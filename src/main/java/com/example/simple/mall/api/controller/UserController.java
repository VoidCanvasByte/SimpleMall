package com.example.simple.mall.api.controller;

import com.example.simple.mall.api.service.UserService;
import com.example.simple.mall.common.dto.UserDTO;
import com.example.simple.mall.common.enu.ResponseEnum;
import com.example.simple.mall.common.response.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户控制层
 *
 * @author sunny
 * @since 2025/05/05
 */
@RestController
@RequestMapping("/apply/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 用户注册申请
     *
     * @author sunny
     * @since 2025/05/05@return@return {@code ResponseResult,ResponseResult }
     */
    @PostMapping("/add")
    public ResponseResult<UserDTO> addUser(@Validated @RequestBody UserDTO userDto) {
        userService.addUser(userDto);
        return ResponseResult.out(ResponseEnum.SUCCESS);
    }
}
