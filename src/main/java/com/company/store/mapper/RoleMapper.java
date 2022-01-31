package com.company.store.mapper;

import com.company.store.domain.Role;
import com.company.store.dto.RoleDTO;
import org.mapstruct.Mapper;


@Mapper(uses = {RoleMapperResolver.class}, componentModel = "spring")
public interface RoleMapper {

    Role toModel(RoleDTO roleDTO);

    RoleDTO toDTO(Role role);
}
