package com.example.demo;

import com.example.demo.repository.*;
import com.example.demo.tables.Pizza;
import com.example.demo.tables.Role;
import com.example.demo.tables.User;
import com.sipios.springsearch.anotation.SearchSpec;
import org.hibernate.annotations.Parameter;
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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class HomeCtrl {

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

    @RequestMapping("/secure")
    public String secure(Principal principal, Model model) {
        String username = principal.getName();
        model.addAttribute("user", userRepository.findByUsername(username));
        return "secure";
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
        ResponseEntity<List<User>> responseEntity = new ResponseEntity<>(userRepository.findAll(Specification.where(specs)), HttpStatus.OK);
        model.addAttribute("responseEntity", responseEntity);
        return "admin";
    }

    @GetMapping("/search2")
    public String getResult(Model model) {
        return "index";
    }

    @GetMapping("/search3")
    public String search(@PathVariable("username") String username, Model model) {
        User user = userRepository.findByUsername(username);
        model.addAttribute("user", user);
        return "admin";
    }

    @PostMapping("/search3")
    public String search3(@ModelAttribute("keyword") String keyword, Model model){
        model.addAttribute("user", userRepository.findByUsername(keyword));
        return "search3";

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

    @PostMapping("/order")
    public String processOrder(@Valid @ModelAttribute("pizza") Pizza pizza, BindingResult result, Model model, Principal principal) {
        if (result.hasErrors()) {
            String username = principal.getName();
            model.addAttribute("user", userRepository.findByUsername(username));
            model.addAttribute("alltoppings", toppingRepository.findAll());
            return "order";
        } else {
            confirmPizza = pizza;
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
        pizzaRepository.save(confirmPizza);
        String username = principal.getName();
        userRepository.findByUsername(username).pizzas.add(confirmPizza);
        return "redirect:/";
    }

    @RequestMapping("/menu")
    public String menu(Model model) {
        model.addAttribute("specialtypizzas",
                pizzaRepository.findPizzasBySpecialtyTrue());
        return "menu";
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
        return "admin";
    }
}
