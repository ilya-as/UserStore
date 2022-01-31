package com.company.store.mapper;

import com.company.store.domain.User;
import com.company.store.dto.UserDTO;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(uses = {RoleMapper.class, UserMapperResolver.class},componentModel = "spring")
public interface UserMapper {

    @Mapping(source = "userDTO.roles", target = "roles")
    User toModel(UserDTO userDTO);

    @Mapping(source = "user.roles", target = "roles")
    UserDTO toDTO(User user);

}
