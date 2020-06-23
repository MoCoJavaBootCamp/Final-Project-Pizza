package com.example.demo.dao;

import org.springframework.data.repository.CrudRepository;

import java.util.Set;

import com.example.demo.modal.Role;

public interface RoleRepository extends CrudRepository<Role, Long> {
    Set<Role> findAll();
}
