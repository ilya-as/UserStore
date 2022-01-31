package com.company.store.service;

import com.company.store.domain.User;
import com.company.store.domain.UserView;

import java.util.List;

public interface IUserService {

    User save(User user);

    String delete(Long userId);

    User update(Long userId, User user);

    List<UserView> getAll();

    User getByID(Long userId);

}
