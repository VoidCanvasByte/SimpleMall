package com.example.simple.mall.api.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.simple.mall.api.mapper.UserMapper;
import com.example.simple.mall.api.service.UserService;
import com.example.simple.mall.common.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 用户 服务实现类
 *
 * @author sunny
 * @since 2025/05/05
 */
@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
