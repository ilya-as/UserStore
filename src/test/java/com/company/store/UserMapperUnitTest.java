package com.company.store;

import com.company.store.domain.Role;
import com.company.store.domain.User;
import com.company.store.dto.RoleDTO;
import com.company.store.dto.UserDTO;
import com.company.store.exception.RoleNotFoundException;
import com.company.store.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import javax.transaction.Transactional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


@ActiveProfiles("test")
@TestPropertySource("classpath:test.properties")
@SpringBootTest
public class UserMapperUnitTest {

    @Autowired
    UserMapper userMapper;

    @Test
    void testMappingUserToUserDTO() {

        Role role = new Role();
        role.setTitle("ADMIN");

        User user = new User();
        user.setName("John Doe");
        user.getRoles().add(role);

        UserDTO userDTO = userMapper.toDTO(user);

        Set<RoleDTO> roles = userDTO.getRoles();
        RoleDTO roleDTO = roles.iterator().next();

        assertThat(userDTO.getName()).isEqualTo("John Doe");
        assertThat(roleDTO.getTitle()).isEqualTo(role.getTitle());
    }

    @Transactional
    @Test
    void testMappingUserDTOToUser() {

        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setTitle("ADMIN");

        UserDTO userDTO = new UserDTO();
        userDTO.setName("John Doe");
        userDTO.getRoles().add(roleDTO);

        User user = userMapper.toModel(userDTO);
        Set<Role> roles = user.getRoles();
        Role role = roles.iterator().next();

        assertThat(user.getName()).isEqualTo("John Doe");
        assertThat(user.getId()).isEqualTo(1L);
        assertThat(role.getTitle()).isEqualTo(roleDTO.getTitle());
        assertThat(role.getId()).isEqualTo(1L);
    }

    @Test
    void testMappingUserDTOToUserWhenRoleNotExist() {

        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setTitle("Role");

        UserDTO userDTO = new UserDTO();
        userDTO.setName("John Doe");
        userDTO.getRoles().add(roleDTO);

        assertThrows(RoleNotFoundException.class, () -> userMapper.toModel(userDTO));
    }
}
