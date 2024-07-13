package ru.itmentor.spring.boot_security.demo.service;

import ru.itmentor.spring.boot_security.demo.model.Role;

import java.util.List;
import java.util.Optional;

public interface RoleService {
    Optional<Role> getRoleByName(String name);

    Role getByName(String name);

    Role getRoleById(Long id);

    List<Role> allRoles();

}
