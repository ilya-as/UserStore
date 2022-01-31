package com.company.store.repository;

import com.company.store.domain.UserView;
import org.springframework.data.repository.Repository;

import java.util.List;


public interface UserViewRepository extends Repository<UserView, Long> {
    List<UserView> findAll();
}
