package com.example.demo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ToppingRepository extends CrudRepository<Topping, Long> {
    List<Topping> findTop3ByCountIsNotNullOrderByCountDesc();
    Topping findToppingByName(String topping);
}
