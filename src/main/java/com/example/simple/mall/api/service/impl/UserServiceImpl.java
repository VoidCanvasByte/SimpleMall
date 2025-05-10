package com.example.simple.mall.api.service.impl;

import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.simple.mall.api.mapStruct.UserMapperStruct;
import com.example.simple.mall.api.mapper.UserMapper;
import com.example.simple.mall.api.service.UserService;
import com.example.simple.mall.common.dto.UserDTO;
import com.example.simple.mall.common.entity.UserEntity;
import org.springframework.security.core.userdetails.User;
import com.example.simple.mall.common.enu.ResponseEnum;
import com.example.simple.mall.common.enu.UserStatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.simple.mall.common.utils.PasswordRelatedUtil.enCode;
import static com.example.simple.mall.common.utils.PasswordRelatedUtil.matches;

/**
 * 用户 服务实现类
 *
 * @author sunny
 * @since 2025/05/05
 */
@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements UserService {


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
        //校验邮箱的格式是否正确
        boolean email1 = Validator.isEmail(email);
        if (!email1) {
            throw new RuntimeException(ResponseEnum.USER_EMAIL_IF_CORRECT.getMessage());
        }
        //根据邮箱的信息去数据库中查询数据
        UserDTO user = userMapper.selectUserByEmail(email, null);
        if (!ObjectUtil.isEmpty(user)) {
            throw new RuntimeException(ResponseEnum.USER_EMAIL_EXIST.getMessage());
        }
        UserEntity userNew = UserMapperStruct.INSTANCE.userDtoToEntity(userDto);
        if (StrUtil.isEmpty(userNew.getUserName())) {
            String userName = "user" + System.currentTimeMillis();
            userNew.setUserName(userName);
        }
        //密码加密(BCryptPasswordEncoder)
        String unCodePassword = enCode(userNew.getPassword());
        userNew.setPassword(unCodePassword);
        userMapper.insertUserInfo(userNew);
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
            UserEntity userOld = userMapper.selectById(id);
            if (ObjectUtil.isEmpty(userOld)) {
                throw new RuntimeException(ResponseEnum.USER_NOT_EXIST.getMessage());
            }
            //根据ID查询用户的状态，确保正常在使用，才可以更新用户的信息
            if (ObjectUtil.equals(userOld.getStatus(), UserStatusEnum.SEAL.getCode())) {
                throw new RuntimeException(ResponseEnum.USER_STATUS_IS_UNUSED.getMessage());
            }
            //对象转换
            UserEntity user = UserMapperStruct.INSTANCE.userDtoToEntity(userDto);


            //更新密码，对旧密码进行校验
            String password = userDto.getPassword();
            boolean match = matches(password, userOld.getPassword());
            if (!match) {
                throw new RuntimeException(ResponseEnum.USER_PASSWORD_IS_WRONG.getMessage());
            }
            //更新用户的信息
            this.updateById(user);
        } else {
            throw new RuntimeException(ResponseEnum.USER_ID_IS_EMPTY.getMessage());
        }
    }

    /**
     * 用户注销
     *
     * @param userId 用户ID
     * @author sunny
     * @since 2025/05/05@return
     */
    @Override
    public void userLogout(String userId) {
        QueryWrapper<User> userWrapper = new QueryWrapper<>();
        userWrapper.in("id", userId);
        UserEntity userOld = userMapper.selectById(userWrapper);
        //用户进行封存
        if (ObjectUtil.isEmpty(userOld)) {
            throw new RuntimeException(ResponseEnum.USER_NOT_EXIST.getMessage());
        }
        //对用户信息进行封存
        QueryWrapper<UserEntity> userWrapperUpdate = new QueryWrapper<>();
        userWrapperUpdate.eq("status", UserStatusEnum.SEAL.getCode());
        userWrapperUpdate.eq("id", userId);
        userMapper.update(userWrapperUpdate);
    }

    /**
     * 用户登陆
     *
     * @param email    用户邮件
     * @param password 用户密码
     * @return @return {@code UserDTO }
     * @author sunny
     * @since 2025/05/05
     */
    @Override
    public User login(String email, String password) {
        //查询数据库中存储的邮箱的码值
        UserDTO userDTO = userMapper.selectUserByEmail(email, null);
        if (ObjectUtil.isNull(userDTO)) {
            throw new RuntimeException(ResponseEnum.USER_NOT_EXIST.getMessage());
        }
        boolean match = matches(password, userDTO.getPassword());
        if (!match) {
            throw new RuntimeException("密码错误");
        }
        List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList("ROLE_" + userDTO.getRole());
        return new User(userDTO.getEmail(), userDTO.getPassword(), authorities);
    }
}
