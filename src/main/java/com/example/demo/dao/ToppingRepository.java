package com.example.demo.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

import com.example.demo.modal.Topping;

@Repository
public interface ToppingRepository extends CrudRepository<Topping, Long> {
    List<Topping> findTop3ByCountIsNotNullOrderByCountDesc();
    Topping findToppingByName(String topping);
    Topping findToppingById(Long id);
    Set<Topping> findAllByEnabledForUserIsTrue();
    Set<Topping> findAll();
}
