package com.example.simple.mall.api.mapStruct;


import com.example.simple.mall.common.dto.user.UserDTO;
import com.example.simple.mall.common.dto.user.UserInfoDTO;
import com.example.simple.mall.common.dto.user.UserReturnInfoDTO;
import com.example.simple.mall.common.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * UserMapperStruct
 *
 * @author sunny
 * @since 2025/05/05
 */
@Mapper
public interface UserMapperStruct {
    UserMapperStruct INSTANCE = Mappers.getMapper(UserMapperStruct.class);

    /**
     * userDtoToEntity
     *
     * @param userDto userDto
     * @return @return {@code UserEntity }
     * @author sunny
     * @since 2025/06/06
     */
    UserEntity userDtoToEntity(UserDTO userDto);


    /**
     * userEntity to UserReturnInfoDTO
     *
     * @param userEntity userEntity
     * @return @return {@code UserReturnInfoDTO }
     * @author sunny
     * @since 2025/05/31
     */
    UserReturnInfoDTO userEntityToUserReturnInfoDTO(UserEntity userEntity);

    /**
     * userInfoDTOToEntity
     *
     * @param userInfoDTO userInfoDTO
     * @return @return {@code UserEntity }
     * @author sunny
     * @since 2025/06/06
     */
    UserEntity userInfoDTOToEntity(UserInfoDTO userInfoDTO);
}