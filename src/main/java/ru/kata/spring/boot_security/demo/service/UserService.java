package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kata.spring.boot_security.demo.entity.User;

import java.util.List;

public interface UserService extends UserDetailsService {
    User findByUsername(String username);
    List<User> getUsersList();
    void save(User user);

    User getUserById(int id);
}
