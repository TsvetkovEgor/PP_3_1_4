package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kata.spring.boot_security.demo.entity.User;

import java.util.List;

public interface UserService extends UserDetailsService {
    User findByUsername(String username);
    User getUserByMail(String mail);

    List<User> getUsersList();

    void save(User user);

    User getUserById(int id);

    void update(User user);

    void delete(int id);
}
