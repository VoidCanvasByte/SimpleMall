package com.example.simple.mall.api.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.simple.mall.api.mapper.UserMapper;
import com.example.simple.mall.api.service.UserService;
import com.example.simple.mall.common.dto.UserDTO;
import com.example.simple.mall.common.entity.User;
import com.example.simple.mall.common.enu.ResponseEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private UserMapper userMapper;


    /**
     * 用户的注册申请
     *
     * @author sunny
     * @since 2025/05/05
     */
    @Override
    public void addUser(UserDTO userDto) {
        String email = userDto.getEmail();
        //根据邮箱的信息去数据库中查询数据
        User user = userMapper.selectUser(email);
        boolean empty = ObjectUtil.isEmpty(user);
        if (empty) {
            //如果数据库中没有数据，则进行插入
            User user1 = new User();
            user1.setEmail(email);
            user1.setPassword(userDto.getPassword());
            user1.setUserName(userDto.getUserName());
            user1.setUserGender(userDto.getUserGender());
            userMapper.insert(user1);
        } else {
            //如果数据库中有数据，则抛出异常
            throw new RuntimeException(ResponseEnum.USER_EXIST.getMessage());
        }
    }

    /**
     * 更新用户信息(包括密码的更新)
     *
     * @author sunny
     * @since 2025/05/05
     */
    @Override
    public void updateUser(UserDTO userDto) {
        Long id = userDto.getId();
        if (ObjectUtil.isNotEmpty(id)) {
            //进行用户信息的更新
            User user = new User();
            BeanUtil.copyProperties(userDto, user);
            //更新用户的信息
            this.updateById(user);
        } else {
            throw new RuntimeException(ResponseEnum.USER_ID_IS_EMPTY.getMessage());
        }
    }
}
