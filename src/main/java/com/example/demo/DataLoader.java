package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {
    @Autowired
    ToppingRepository toppingRepository;

    @Override
    public void run (String... strings) throws Exception {
        Topping topping;
        topping = new Topping("Pepperoni");
        toppingRepository.save(topping);
        topping = new Topping("Sausage");
        toppingRepository.save(topping);
        topping = new Topping("Chicken");
        toppingRepository.save(topping);
        topping = new Topping("Bacon");
        toppingRepository.save(topping);
        topping = new Topping("Olives");
        toppingRepository.save(topping);
        topping = new Topping("Peppers");
        toppingRepository.save(topping);
        topping = new Topping("Pineapple");
        toppingRepository.save(topping);
        topping = new Topping("Onion");
        toppingRepository.save(topping);
        topping = new Topping("Tomato");
        toppingRepository.save(topping);
    }
}
