package com.company.store;

import com.company.store.domain.Role;
import com.company.store.domain.User;
import com.company.store.domain.UserView;
import com.company.store.exception.UserExistsException;
import com.company.store.exception.UserNotFoundException;
import com.company.store.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import javax.transaction.Transactional;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


@ActiveProfiles("test")
@TestPropertySource("classpath:test.properties")
@SpringBootTest
class UserStoreApplicationTests {

    @Autowired
    private UserServiceImpl userService;

    private User user;
    private Role role;

    @BeforeEach
    public void before() {
        role = new Role();
        role.setTitle("roleTitle");

        user = new User();
        user.setName("userName");
        user.getRoles().add(role);
    }

   @Test()
   @Transactional
    public void save() {
        userService.save(user);
        assertThat(user.getId()).isGreaterThan(0);
    }

    @Test
    @Transactional
    public void saveDuplicateUser() {
        userService.save(user);
        assertThrows(UserExistsException.class, () -> userService.save(user));
    }

    @Test
    @Transactional
    public void getByID() {
        userService.save(user);
        User foundUser= userService.getByID(user.getId());
        assertThat(user.getId()).isEqualTo(foundUser.getId());
    }

    @Test
    @Transactional
    public void getByIDWhenUserNotFound() {
        userService.save(user);
        assertThrows(UserNotFoundException.class, () -> userService.getByID(99L));
    }

    @Test
    @Transactional
    public void update() {
        userService.save(user);
        user.setName("newName");
        userService.update(user.getId(),user);
        User updatedUser =userService.getByID(user.getId());
        assertThat(user.getName()).isEqualTo(updatedUser.getName());
    }

    @Test
    @Transactional
    public void delete() {
        userService.save(user);
        userService.delete(user.getId());
        assertThrows(UserNotFoundException.class, () -> userService.getByID(user.getId()));
    }

    @Test
    @Transactional
    public void getAll(){
        List<UserView> userViewList = Arrays.asList(
                new UserView(1L,"John Doe","ADMIN, MANAGER, PRORAB"),
                new UserView(2L,"Eric Smith","PRORAB"));
        List<UserView> userViewListResult = userService.getAll();
       assertThat(userViewListResult).containsAll(userViewList);
    }
}
