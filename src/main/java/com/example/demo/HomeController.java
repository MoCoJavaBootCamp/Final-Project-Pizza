package com.example.demo;

import com.example.demo.repository.*;
import com.example.demo.tables.Pizza;
import com.example.demo.tables.Role;
import com.example.demo.tables.Topping;
import com.example.demo.tables.User;
import com.sipios.springsearch.anotation.SearchSpec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class HomeController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    ReportRepository reportRepository;

    @Autowired
    PizzaRepository pizzaRepository;

    @Autowired
    ToppingRepository toppingRepository;

    Pizza confirmPizza;

    @RequestMapping("/myaccount")
    public String myaccount(Principal principal, Model model) {
        String username = principal.getName();
        model.addAttribute("user", userRepository.findByUsername(username));
        return "myaccount";
    }

    @RequestMapping("/register")
    public String showRegistrationPage(Model model) {
        model.addAttribute("user", new User());
            return "register";
    }

    @PostMapping("/register")
    public String processRegistrationPage(@Valid @ModelAttribute("user") User user, BindingResult result, Model model) {
        model.addAttribute("user", user);
        if (result.hasErrors()) {
            return "register";
        }
        model.addAttribute("message", "User Account Created");

        user.setEnabled(true);
        Role role = new Role(user.getUsername(), "ROLE_USER");
        Set<Role> roles = new HashSet<Role>();
        roles.add(role);

        roleRepository.save(role);
        userRepository.save(user);

        return "index";
    }

    @GetMapping("/search")
    public String searchForUser(@SearchSpec Specification<User> specs, Model model) {
        ResponseEntity<List<User>> responseEntity =
                new ResponseEntity<>(userRepository.findAll(Specification.where(specs)), HttpStatus.OK);
        model.addAttribute("responseEntity", responseEntity);
        return "admin";
    }

    @GetMapping("/search2")
    public String getResult(Model model) {
        return "index";
    }

    @RequestMapping("/")
    public String index() { return "index"; }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/logout")
    public String logout() {
        return "redirect:/login?logout=true";
    }

    @GetMapping("/order")
    public String order(Model model, Principal principal) {
        model.addAttribute("pizza", new Pizza());
        String username = principal.getName();
        model.addAttribute("user", userRepository.findByUsername(username));
        model.addAttribute("alltoppings", toppingRepository.findAll());
        return "order";
    }

    //ctrl might be confused with get/post routes
    @PostMapping("/processorder")
    public String processOrder(@Valid @ModelAttribute("pizza") Pizza pizza, BindingResult result, Model model, Principal principal) {
        if (result.hasErrors()) {
            String username = principal.getName();
            model.addAttribute("user", userRepository.findByUsername(username));
            model.addAttribute("alltoppings", toppingRepository.findAll());
            return "order";
        } else {
            confirmPizza = pizza;
            System.out.println(confirmPizza.toString());
            return "redirect:/checkout";
        }
    }

    @GetMapping("/checkout")
    public String checkout (Model model, Principal principal) {
        model.addAttribute("pizza", confirmPizza);
        String username = principal.getName();
        model.addAttribute("user", userRepository.findByUsername(username));
        return "checkout";
    }

    @PostMapping("/checkout")
    public String confirmCheckout (@ModelAttribute("pizza") Pizza pizza, Model model, Principal principal) {
        String username = principal.getName();
        confirmPizza.setUser(userRepository.findByUsername(username));
        confirmPizza.setDate(LocalDateTime.now());
        pizzaRepository.save(confirmPizza);
        User user = userRepository.findByUsername(username);
        Set<Pizza> pizzas = user.getPizzas();
        pizzas.add(confirmPizza);
        user.setPizzas(pizzas);
        userRepository.save(user);
        return "redirect:/";
    }

    @RequestMapping("/menu")
    public String menu(Model model) {
        model.addAttribute("specialtypizzas",
                pizzaRepository.findPizzasBySpecialtyTrue());
        return "menu";
    }

    @RequestMapping("/orderSpecial/{id}")
    public String orderSpecial(@PathVariable("id") long id, Model model) {
        confirmPizza = new Pizza(pizzaRepository.findById(id).get());
        System.out.println(confirmPizza);
        return "redirect:/checkout";
    }


    @RequestMapping("/orderhistory")
    public String orderHistory(Model model, Principal principal) {
        String username = principal.getName();
        Set<Pizza> allpizzasbyuser = pizzaRepository.findAllByUserUsername(username);
        model.addAttribute("pizzaorderhistory", allpizzasbyuser);
        return "orderhistory";
    }

    /* === ADMIN ROUTES === */
    @RequestMapping("/admin")
    public String admin(Model model) {
        double totalsales = 0.00;
        Set<Pizza> allpizzas = pizzaRepository.findAll();
        for (Pizza pizza : allpizzas) {
            totalsales += pizza.getPrice();
        }
        String totalSalesStr = String.format("%.2f", totalsales);
        totalSalesStr = "$" + totalSalesStr;

        model.addAttribute("allusers", userRepository.findAll());
        model.addAttribute("totalsales", totalSalesStr);
        model.addAttribute("toptoppings", toppingRepository
                .findTop3ByCountIsNotNullOrderByCountDesc());
        model.addAttribute("newtopping", new Topping());
        model.addAttribute("alltoppings", toppingRepository.findAll());
        return "admin";
    }

    @RequestMapping("/addtopping")
    public String addTopping(@Valid @ModelAttribute("newtopping")
                                         Topping toppingToAddInForm, BindingResult result) {
        if (result.hasErrors()) {
            toppingRepository.save(toppingToAddInForm);
            return "redirect:/admin";
        } else {
            toppingRepository.save(toppingToAddInForm);
            return "redirect:/admin";        }
    }

    @RequestMapping("/update/{id}")
    public String toggleTopping(@PathVariable("id") long id, Model model) {
        Topping topping = toppingRepository.findToppingById(id);
        System.out.println("update topping before: " + topping.toString());
        topping.setEnabledForUser();
        toppingRepository.save(topping);
        System.out.println("update topping after: " + topping.toString());
        model.addAttribute("topping", topping);
        return "redirect:/admin";
    }

    @RequestMapping("/delete/{id}")
    public String deleteTopping(@PathVariable("id") long id) {
        toppingRepository.deleteById(id);
        return "redirect:/admin";
    }

    @RequestMapping("/update/{id}")
    public String updateCar(@PathVariable("id") long id, Model model){
        model.addAttribute("user", userRepository.findById(id).get());
        return "my_account";
    }

}
