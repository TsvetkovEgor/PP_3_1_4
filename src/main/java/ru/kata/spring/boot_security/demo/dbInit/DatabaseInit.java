package ru.kata.spring.boot_security.demo.dbInit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.entity.Role;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.annotation.PostConstruct;
import java.util.Collections;

@Component
public class DatabaseInit {
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public DatabaseInit(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostConstruct
    public void initDbUsers() {
        roleService.addRole(new Role("ROLE_ADMIN"));
        roleService.addRole(new Role("ROLE_USER"));

        userService.save(new User(
                "useradmin",
                "useradmin@mail.ru",
                "useradmin",
                roleService.getRoles()));

        userService.save(new User(
                "user",
                "user@mail.ru",
                "user",
                Collections.singletonList(roleService.findRoleByName("ROLE_USER"))));

        userService.save(new User(
                "admin",
                "admin@mail.ru",
                "admin",
                Collections.singletonList(roleService.findRoleByName("ROLE_ADMIN"))));

    }
}
