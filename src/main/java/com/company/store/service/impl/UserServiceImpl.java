package com.company.store.service.impl;

import com.company.store.domain.User;
import com.company.store.domain.UserView;
import com.company.store.exception.UserExistsException;
import com.company.store.exception.UserNotFoundException;
import com.company.store.repository.UserRepository;
import com.company.store.repository.UserViewRepository;
import com.company.store.service.IUserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    private final UserRepository userRepository;

    private final UserViewRepository userViewRepository;


    public UserServiceImpl(UserRepository userRepository, UserViewRepository userViewRepository) {
        this.userRepository = userRepository;
        this.userViewRepository = userViewRepository;
    }

    @Override
    public User save(User user) {
        if (userRepository.findByName(user.getName()).isPresent()) {
            throw new UserExistsException(user.getName());
        }
        return userRepository.save(user);
    }

    @Override
    public String delete(Long userId) {
        userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
        userRepository.deleteById(userId);
        return "User with id: " + userId + " deleted successfully!";
    }

    @Override
    public User update(Long userId, User user) {
        userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
        user.setId(userId);
        return userRepository.save(user);
    }

    @Override
    public User getByID(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
    }

    @Override
    public List<UserView> getAll() {
        return userViewRepository.findAll();
    }

}
