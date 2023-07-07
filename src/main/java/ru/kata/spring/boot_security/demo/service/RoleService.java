package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.entity.Role;

import java.util.List;
import java.util.Set;

public interface RoleService {
    Role findRoleByName(String name);

    Set<Role> getRoles();

    Role findRoleById(int id);

    void addRole(Role role);

}
