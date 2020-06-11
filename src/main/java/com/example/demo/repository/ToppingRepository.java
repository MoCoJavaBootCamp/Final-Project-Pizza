package com.example.demo.repository;

import com.example.demo.tables.Topping;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ToppingRepository extends CrudRepository<Topping, Long> {
    List<Topping> findTop3ByCountIsNotNullOrderByCountDesc();
    Topping findToppingByName(String topping);
    List<Topping> findAll();
}
