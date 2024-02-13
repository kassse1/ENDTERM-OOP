package com.example.postgresdemo.service;

import com.example.postgresdemo.model.Role;
import com.example.postgresdemo.model.User;
import com.example.postgresdemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username);

        return new org.springframework.security.core.userdetails.User(username, user.getPassword(), buildUserAuthority(user.getRoles()));
    }

    private List<GrantedAuthority> buildUserAuthority(List<Role> userRoles) {

        List<GrantedAuthority> authorities = userRoles.stream().map(userRole -> new SimpleGrantedAuthority(userRole.getRolename())).collect(Collectors.toList());

        return authorities;
    }

}
