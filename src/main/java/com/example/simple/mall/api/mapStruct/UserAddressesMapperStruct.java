package com.example.simple.mall.api.mapStruct;


import com.example.simple.mall.common.dto.user.AddressDTO;
import com.example.simple.mall.common.dto.user.UserReturnInfoDTO;
import com.example.simple.mall.common.entity.UserAddressesEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * UserAddressesMapperStruct
 *
 * @author sunny
 * @since 2025/05/31
 */
@Mapper
public interface UserAddressesMapperStruct {

    UserAddressesMapperStruct INSTANCE = Mappers.getMapper(UserAddressesMapperStruct.class);


    /**
     * addressDTO to UserAddressesEntity
     *
     * @param addressDTO addressDTO
     * @return @return {@code UserAddressesEntity }
     * @author sunny
     * @since 2025/05/31
     */
    UserAddressesEntity addressDTOToUserAddressesEntity(AddressDTO addressDTO);

    /**
     * entitiesToDto
     *
     * @param entities entities
     * @author sunny
     * @since 2025/05/31@return @return {@code UserReturnInfoDTO.UserAddressesReturnDTO }
     */
    List<UserReturnInfoDTO.UserAddressesReturnDTO> entitiesToDto(List<UserAddressesEntity> entities);
}