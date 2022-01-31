package com.company.store.mapper;

import com.company.store.domain.Role;
import com.company.store.dto.RoleDTO;
import com.company.store.exception.RoleNotFoundException;
import com.company.store.repository.RoleRepository;
import org.mapstruct.ObjectFactory;
import org.mapstruct.TargetType;
import org.springframework.stereotype.Component;


@Component
public class RoleMapperResolver {

    private RoleRepository roleRepository;

    public RoleMapperResolver(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @ObjectFactory
    public Role resolve(RoleDTO dto, @TargetType Class<Role> type) {
        return roleRepository.getRoleByTitle(dto.getTitle())
                .orElseThrow(()->new RoleNotFoundException(dto.getTitle()));
    }

}
