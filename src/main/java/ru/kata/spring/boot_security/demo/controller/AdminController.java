package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring.boot_security.demo.entity.Role;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;
import ru.kata.spring.boot_security.demo.util.UserValidator;

import javax.validation.Valid;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminController {


    private final UserService userService;
    private final RoleService roleService;
    private final UserValidator userValidator;

    @Autowired
    public AdminController(UserService userService, RoleService roleService, UserValidator userValidator) {
        this.userService = userService;
        this.roleService = roleService;
        this.userValidator = userValidator;
    }

    @GetMapping
    public String getAllUsers(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("userById",user);
        model.addAttribute("users", userService.getUsersList());
        Set<Role> roles = roleService.getRoles();
        model.addAttribute("roles", roles);
        return "admin/adminListUsers";
    }

    @GetMapping("/add")
    public String newUser(Model model) {
        User userAutentificated = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("userById", userAutentificated);
        User user = new User();
        Set<Role> roles = roleService.getRoles();
        model.addAttribute("user", user);
        model.addAttribute("roles", roles);
        return "admin/add";
    }

    @PostMapping
    public String createUser(@ModelAttribute("user") @Valid User user,
                             BindingResult bindingResult, Model model) {
        User userAutentificated = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        userValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("roles", roleService.getRoles());
            model.addAttribute("userById", userAutentificated);
            return "admin/add";
        }
        userService.save(user);
        return "redirect:/admin";
    }

    @PatchMapping("/{id}/update")
    public String update(@ModelAttribute("user") @Valid User user, BindingResult bindingResult, Model model) {
        User userAutentificated = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        userValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("user", user);
            model.addAttribute("userById", userAutentificated);
            model.addAttribute("roles", roleService.getRoles());
            return "redirect:/admin";
        }
        userService.update(user);
        return "redirect:/admin";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id) {
        userService.delete(id);
        return "redirect:/admin";
    }

}
