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
        topping = new Topping("Spicy Italian Sausage");
        toppingRepository.save(topping);
        topping = new Topping("Chicken");
        toppingRepository.save(topping);
        topping = new Topping("Bacon");
        toppingRepository.save(topping);
        topping = new Topping("Canadian Bacon");
        toppingRepository.save(topping);
        topping = new Topping("Beef");
        toppingRepository.save(topping);
        topping = new Topping("Olives");
        toppingRepository.save(topping);
        topping = new Topping("Green Peppers");
        toppingRepository.save(topping);
        topping = new Topping("Pineapple");
        toppingRepository.save(topping);
        topping = new Topping("Onions");
        toppingRepository.save(topping);
        topping = new Topping("Tomato");
        toppingRepository.save(topping);
        topping = new Topping("Mushrooms");
        toppingRepository.save(topping);

        Pizza pizza;


        /* === SPECIALTY PIZZAS === */

        // The Works
        pizza = new Pizza();
        pizza.setName("The Works");
        pizza.setSpecialty(true);
        pizza.setSauce("Classic Tomato");

        Set<Topping> toppings3 = new HashSet<>();
        toppings3.add(toppingRepository.findToppingByName("Spicy Italian Sausage"));
        toppings3.add(toppingRepository.findToppingByName("Pepperoni"));
        toppings3.add(toppingRepository.findToppingByName("Canadian Bacon"));
        toppings3.add(toppingRepository.findToppingByName("Green Peppers"));
        toppings3.add(toppingRepository.findToppingByName("Olives"));
        toppings3.add(toppingRepository.findToppingByName("Onions"));

        pizza.setToppings(toppings3);
        pizza.setPrice(pizza.getPrice());
        pizza.setImage("/img/the_works_pizza.jpg");
        pizza.setDescription("A dazzling dish made with spicy italian sausage, fresh-cut pepperoni, sizzling Canadian bacon, green peppers, olives, and onions. An American classic.");
        pizzaRepository.save(pizza);

        //Veggie
        Set<Topping> toppings2 = new HashSet<>();
        toppings2.add(toppingRepository.findToppingByName("Olives"));
        toppings2.add(toppingRepository.findToppingByName("Green Peppers"));
        toppings2.add(toppingRepository.findToppingByName("Pineapples"));
        toppings2.add(toppingRepository.findToppingByName("Onions"));
        pizza = new Pizza();
        pizza.setSpecialty(true);
        pizza.setName("Veggie");
        pizza.setSauce("Tomato");
        pizza.setToppings(toppings2);
        pizza.setPrice(pizza.getPrice());
        pizza.setImage("/img/veggie_pizza.jpg");
        pizza.setDescription("For the vegetarians out there, we have the pizza for you. Olives, sliced green peppers, fresh pineapple and onions. What more do you need?");
        pizzaRepository.save(pizza);

        //Meat Lovers
        Set<Topping> toppings = new HashSet<>();
        toppings.add(toppingRepository.findToppingByName("Pepperoni"));
        toppings.add(toppingRepository.findToppingByName("Sausage"));
        toppings.add(toppingRepository.findToppingByName("Chicken"));
        toppings.add(toppingRepository.findToppingByName("Bacon"));

        pizza = new Pizza();
        pizza.setSpecialty(true);
        pizza.setSauce("Tomato");
        pizza.setName("Meat Lovers");
        pizza.setToppings(toppings);
        pizza.setPrice(pizza.getPrice());
        pizza.setImage("/img/meat_lovers_pizza.jpg");
        pizza.setDescription("A pizza for the carnivores. Pepperoni, gourmet sausage, chicken, and crispy bacon. Cruelty-free and a joy to see. Time to tuck in.");
        pizzaRepository.save(pizza);

        //Hawaiian
        Set<Topping> toppings4 = new HashSet<>();
        toppings.add(toppingRepository.findToppingByName("Canadian Bacon"));
        toppings.add(toppingRepository.findToppingByName("Pineapple"));
        toppings.add(toppingRepository.findToppingByName("Bacon"));

        pizza = new Pizza();
        pizza.setSpecialty(true);
        pizza.setSauce("Tomato");
        pizza.setName("Hawaiian");
        pizza.setToppings(toppings4);
        pizza.setPrice(pizza.getPrice());
        pizza.setImage("https://www.thespruceeats.com/thmb/c7q1QAXOnuTNPqp9J-pvRnzl46E=/960x0/filters:no_upscale():max_bytes(150000):strip_icc():format(webp)/IMG_4618fhor-a98bebf4c7e743a8bbfd6071b5dafc12.jpg");
        pizza.setDescription("Get a taste of the tropics. This super delicious pizza is loaded with sweet, " +
                "juicy pineapple tidbits, julienne-cut Canadian bacon, and hickory-smoked bacon");
        pizzaRepository.save(pizza);

    }
}
