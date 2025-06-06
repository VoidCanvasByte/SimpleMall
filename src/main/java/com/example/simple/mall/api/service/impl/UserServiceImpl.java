package com.example.simple.mall.api.service.impl;

import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.simple.mall.api.mapStruct.UserAddressesMapperStruct;
import com.example.simple.mall.api.mapStruct.UserMapperStruct;
import com.example.simple.mall.api.mapper.UserAddressesMapper;
import com.example.simple.mall.api.mapper.UserMapper;
import com.example.simple.mall.api.service.UserService;
import com.example.simple.mall.common.dto.LoginRequestDTO;
import com.example.simple.mall.common.dto.user.*;
import com.example.simple.mall.common.entity.UserAddressesEntity;
import com.example.simple.mall.common.entity.UserEntity;
import com.example.simple.mall.common.enu.ResponseEnum;
import com.example.simple.mall.common.enu.UserStatusEnum;
import com.example.simple.mall.common.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

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

    @Value("${zip.code.address}")
    private String zipCodeAddress;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserAddressesMapper userAddressesMapper;


    /**
     * 用户的注册申请
     *
     * @author sunny
     * @since 2025/05/05
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addUser(UserInfoDTO userInfoDTO) {
        String email = userInfoDTO.getEmail();
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
        UserEntity userNew = UserMapperStruct.INSTANCE.userInfoDTOToEntity(userInfoDTO);
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
     * 判断用户是否存在(用户不存在返回true)
     *
     * @param userId 用户ID
     * @author sunny
     * @since 2025/05/24@return @return {@code Boolean }@return @return {@code Boolean }
     */
    @Override
    public Boolean judgeUserIfNull(Long userId) {
        QueryWrapper<UserEntity> userWrapper = new QueryWrapper<>();
        userWrapper.eq("id", userId);
        userWrapper.eq("status", UserStatusEnum.FRIEND.getCode());
        return ObjectUtils.isEmpty(this.getOne(userWrapper));
    }

    /**
     * 更新用户基础信息(包括密码的更新)
     *
     * @author sunny
     * @since 2025/05/05
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
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
            //密码加密(BCryptPasswordEncoder)
            String unCodePassword = enCode(user.getPassword());
            user.setPassword(unCodePassword);
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
    @Transactional(rollbackFor = Exception.class)
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
            throw new RuntimeException(ResponseEnum.USER_PASSWORD_IS_WRONG.getMessage());
        }
        List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList("ROLE_" + userDTO.getRole());
        return new User(userDTO.getUserName(), userDTO.getPassword(), authorities);
    }

    /**
     * 获取用户的token
     *
     * @param user         user
     * @param loginRequest loginRequest
     * @return @return {@code Map<String, User> }
     * @author sunny
     * @since 2025/05/16
     */
    @Override
    public Map<String, UserEntity> resultToken(User user, LoginRequestDTO loginRequest) {
        QueryWrapper<UserEntity> userWrapperUpdate = new QueryWrapper<>();
        userWrapperUpdate.eq("email", loginRequest.getEmail());
        UserEntity userEntity = userMapper.selectOne(userWrapperUpdate);
        String token = JwtUtils.generateToken(userEntity.getId());
        Map<String, UserEntity> resultMap = new HashMap<>();
        resultMap.put(token, userEntity);
        return resultMap;
    }

    /**
     * 维护用户地址信息
     *
     * @param userAddressesDTO userAddressesDTO
     * @author sunny
     * @since 2025/05/31
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void userMaintainInfo(UserAddressesDTO userAddressesDTO) {
        //根据邮编自动识别地址
        RestTemplate restTemplate = new RestTemplate();
        String postalCode = userAddressesDTO.getPostalCode();
        String url = zipCodeAddress + postalCode;
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        JSONObject json = JSONObject.parseObject(response.getBody());
        JSONArray results = json.getJSONArray("results");
        if (results != null) {
            if (!ObjectUtil.equal(userAddressesDTO.getId(), null)) {
                QueryWrapper<UserAddressesEntity> userAddresssQueryWrapper = new QueryWrapper<UserAddressesEntity>();
                userAddresssQueryWrapper.eq("id", userAddressesDTO.getId());
                userAddressesMapper.delete(userAddresssQueryWrapper);
                userAddressesDTO.setId(null);
            }
            JSONObject item = Objects.requireNonNull(results).getJSONObject(0);
            AddressDTO javaObject = item.toJavaObject(AddressDTO.class);
            javaObject.setPrefecture(item.getString("address1"));
            javaObject.setCity(item.getString("address2"));
            javaObject.setTown(item.getString("address3"));
            UserAddressesEntity UserAddressesEntity = UserAddressesMapperStruct.INSTANCE.addressDTOToUserAddressesEntity(javaObject);
            BeanUtils.copyProperties(userAddressesDTO, UserAddressesEntity);
            userAddressesMapper.insert(UserAddressesEntity);
        } else {
            throw new RuntimeException(ResponseEnum.PRODUCT_ADDRESS_FALSE.getMessage());
        }
    }

    /**
     * 根据用户ID查询用户信息
     *
     * @param userId userId
     * @return @return {@code List<UserReturnInfoDTO> }
     * @author sunny
     * @since 2025/05/31
     */
    @Override
    public UserReturnInfoDTO selectUserInfo(Long userId) {
        //用户信息
        UserEntity userEntity = userMapper.selectById(userId);

        QueryWrapper<UserAddressesEntity> userQueryWrapper = new QueryWrapper<UserAddressesEntity>();
        userQueryWrapper.eq("user_id", userId);
        List<UserAddressesEntity> userAddressesList = userAddressesMapper.selectList(userQueryWrapper);
        UserReturnInfoDTO userReturnInfo = UserMapperStruct.INSTANCE.userEntityToUserReturnInfoDTO(userEntity);
        List<UserReturnInfoDTO.UserAddressesReturnDTO> userAddressesDTOS = UserAddressesMapperStruct.INSTANCE.entitiesToDto(userAddressesList);
        userReturnInfo.getUserAddressesReturnDTOList().addAll(userAddressesDTOS);
        return userReturnInfo;
    }
}
