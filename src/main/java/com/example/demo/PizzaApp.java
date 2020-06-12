package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PizzaApp {

    public static void main(String[] args) {
        SpringApplication.run(PizzaApp.class, args);
    }

}

// change mapping
// delete toppings from topping repo
// Set<Topping>