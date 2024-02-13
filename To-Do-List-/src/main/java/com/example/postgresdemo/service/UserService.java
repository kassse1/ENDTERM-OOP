package com.example.postgresdemo.service;

import com.example.postgresdemo.model.Role;
import com.example.postgresdemo.model.User;
import com.example.postgresdemo.repository.RoleRepository;
import com.example.postgresdemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @PostConstruct
    public void init() {
        Role role = new Role();
        role.setRolename("ROLE_USER");
        roleRepository.save(role);
    }

    public void save(User user) {
        user.setPassword(user.getPassword());
        user.setRoles(roleRepository.findAll());
        userRepository.save(user);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
