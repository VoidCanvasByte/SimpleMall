package com.example.simple.mall.api.controller;

import com.example.simple.mall.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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

}
