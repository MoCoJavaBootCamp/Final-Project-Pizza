package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FrequentToppingRepo extends JpaRepository<Pizza, Long> {


		@Query("SELECT new com.example.FrequentToppings(p, COUNT(p)) "
				+ " FROM Pizza p "
				+ " GROUP BY p.topping "
				+ " ORDER BY COUNT(p) DESC")
		List<FrequentToppings> findToppingCount();
}
