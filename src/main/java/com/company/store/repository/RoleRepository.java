package com.company.store.repository;

import com.company.store.domain.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


public interface RoleRepository extends CrudRepository<Role, Long> {

    Optional<Role> getRoleByTitle(String title);
}
