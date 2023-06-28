package ru.kata.spring.boot_security.demo.controller;


import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring.boot_security.demo.entity.User;


@Controller
@RequestMapping("/user")
public class UserController {


    @GetMapping
    public String showProfilePage(@AuthenticationPrincipal User user, Model model) {
        boolean isAdmin = AuthorityUtils.authorityListToSet(user.getRoles())
                .contains("ROLE_ADMIN");
        model.addAttribute("userById", user);
        model.addAttribute("isAdmin", isAdmin);
        return "user/profile";
    }

}
