package com.company.store.controller;

import com.company.store.domain.User;
import com.company.store.domain.UserView;
import com.company.store.dto.UserDTO;
import com.company.store.mapper.UserMapper;
import com.company.store.service.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    private final IUserService userService;
    private final UserMapper userMapper;

    public UserController(IUserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserView>> getAllUsers() {
        List<UserView> userViews = userService.getAll();
        return ResponseEntity.ok(userViews);
    }

    @PostMapping("/add")
    public ResponseEntity<UserDTO> addUser(@RequestBody UserDTO userDTO) {
        User user = userService.save(userMapper.toModel(userDTO));
        UserDTO createdUser = userMapper.toDTO(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable(name = "id") Long userId,
                                              @RequestBody UserDTO userDTO) {
        User user = userService.update(userId, userMapper.toModel(userDTO));
        UserDTO createdUser = userMapper.toDTO(user);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(createdUser);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable(name = "id") Long userId) {
        String message = userService.delete(userId);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(message);
    }

}
