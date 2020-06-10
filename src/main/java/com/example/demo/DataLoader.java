package com.example.demo;
import com.example.demo.repository.PizzaRepository;
import com.example.demo.repository.ToppingRepository;
import com.example.demo.tables.Pizza;
import com.example.demo.tables.Topping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class DataLoader implements CommandLineRunner {
    @Autowired
    ToppingRepository toppingRepository;

    @Autowired
    PizzaRepository pizzaRepository;

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

        Set<Topping> toppings = new HashSet<>();
        toppings.add(toppingRepository.findToppingByName("Pepperoni"));
        toppings.add(toppingRepository.findToppingByName("Sausage"));
        toppings.add(toppingRepository.findToppingByName("Chicken"));
        toppings.add(toppingRepository.findToppingByName("Bacon"));
        Pizza pizza;
        pizza = new Pizza();
        pizza.setName("Meat Lovers");
        pizza.setSauce("Classic Tomato");
        pizza.setToppings(toppings);
        pizza.setPrice(pizza.getPrice());
        pizzaRepository.save(pizza);

        Set<Topping> toppings2 = new HashSet<>();
        toppings.add(toppingRepository.findToppingByName("Olives"));
        toppings.add(toppingRepository.findToppingByName("Peppers"));
        toppings.add(toppingRepository.findToppingByName("Pineapples"));
        toppings.add(toppingRepository.findToppingByName("Onion"));
        pizza = new Pizza();
        pizza.setName("Veggie");
        pizza.setSauce("Classic Tomato");
        pizza.setToppings(toppings2);
        pizza.setPrice(pizza.getPrice());
        pizzaRepository.save(pizza);
    }
}
