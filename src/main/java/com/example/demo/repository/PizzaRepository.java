package com.example.demo.repository;

import com.example.demo.tables.Pizza;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface PizzaRepository extends CrudRepository<Pizza, Long> {
    Set<Pizza> findAll();
    Set<Pizza> findPizzasBySpecialtyTrue();
    Set<Pizza> findAllByUserUsername(String username);
    Pizza findPizzaByUser_Username(String username);
}
