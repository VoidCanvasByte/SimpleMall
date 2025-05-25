package com.example.simple.mall.api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.simple.mall.common.dto.user.UserDTO;
import com.example.simple.mall.common.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * UserMapper
 *
 * @author sunny
 * @since 2025/05/05
 */
@Mapper
public interface UserMapper extends BaseMapper<UserEntity> {

    /**
     * 根据邮箱查询用户信息
     *
     * @param email 邮箱
     * @author sunny
     * @since 2025/05/05@return @return {@code User }
     */
    UserDTO selectUserByEmail(@Param("email") String email,@Param("id") Integer id);

    /**
     * 新增用户信息
     *
     * @author sunny
     * @since 2025/05/05
     */
    void insertUserInfo(UserEntity userNew);
}
