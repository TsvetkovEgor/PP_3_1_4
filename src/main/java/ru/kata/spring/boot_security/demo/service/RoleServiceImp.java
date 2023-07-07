package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.entity.Role;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;

import java.util.HashSet;
import java.util.Set;

@Service
@Transactional(readOnly = true)
public class RoleServiceImp implements RoleService {
    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImp(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role findRoleByName(String name) {
       return roleRepository.findRoleByRole(name);
    }

    @Override
    public Set<Role> getRoles() {
        return new HashSet<>(roleRepository.findAll());
    }

    @Override
    public Role findRoleById(int id) {
        return roleRepository.findById(id).get();
    }
    @Transactional
    @Override
    public void addRole(Role role) {
        roleRepository.save(role);
    }
}
