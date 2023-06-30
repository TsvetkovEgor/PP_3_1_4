package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.entity.Role;

import java.util.List;

public interface RoleService {
    Role findRoleByName(String name);

    List<Role> getRoles();

    Role findRoleById(int id);

    void addRole(Role role);

}
