package com.example.postgresdemo.service;

import com.example.postgresdemo.model.Role;
import com.example.postgresdemo.model.User;
import com.example.postgresdemo.repository.RoleRepository;
import com.example.postgresdemo.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @InjectMocks
    private UserService service;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private UserRepository userRepository;

    @Test
    public void shouldSave() {
        User user = new User();
        user.setPassword("123qwe");

        Role role = new Role();
        role.setRolename("Role");
        List<Role> roleList = new ArrayList<>();
        roleList.add(role);

        when(roleRepository.findAll()).thenReturn(roleList);

        service.save(user);

        verify(userRepository,times(1)).save(user);
        assertEquals(user.getPassword(), "123qwe");
        assertEquals(user.getRoles(), roleList);
    }

    @Test
    public void shouldFindByUserName() {
        service.findByUsername("asd");

        verify(userRepository, times(1)).findByUsername("asd");
    }

}