package com.company.store.mapper;

import com.company.store.domain.User;
import com.company.store.dto.UserDTO;
import com.company.store.repository.RoleRepository;
import com.company.store.repository.UserRepository;
import com.company.store.service.IUserService;
import org.mapstruct.ObjectFactory;
import org.mapstruct.TargetType;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
public class UserMapperResolver {
    private UserRepository userRepository;

    public UserMapperResolver(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @ObjectFactory
    public User resolve(UserDTO userDTO, @TargetType Class<User> type) {
        Optional<User> optionalUser = userRepository.findByName(userDTO.getName());
        User user = optionalUser.orElseGet(User::new);
        return user;
    }
}
