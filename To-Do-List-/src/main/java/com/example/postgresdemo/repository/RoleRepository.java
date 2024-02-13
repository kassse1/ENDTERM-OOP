package com.example.postgresdemo.repository;

import com.example.postgresdemo.model.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RoleRepository extends CrudRepository<Role, Integer> {

    List<Role> findAll();
}
