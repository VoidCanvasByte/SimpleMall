package com.example.simple.mall.api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.simple.mall.common.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * UserMapper
 *
 * @author sunny
 * @since 2025/05/05
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    /**
     * 查询用户的信息
     *
     * @return @return {@code User }
     * @author sunny
     * @since 2025/05/05
     */
    User selectUser(@Param("email") String email);

    /**
     * 新增用户信息
     *
     * @author sunny
     * @since 2025/05/05
     */
    void insertUserInfo(User userNew);
}
