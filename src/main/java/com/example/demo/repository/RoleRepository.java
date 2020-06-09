package com.example.demo.repository;

import com.example.demo.tables.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Long> {
}
