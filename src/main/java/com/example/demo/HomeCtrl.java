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

    

    /* === ADMIN ROUTES === */
    @RequestMapping("/admin")
    public String admin(Model model) {
        double totalcost = 0.00;
        Set<Pizza> allpizzas = pizzaRepository.findAll();
        for (Pizza pizza : allpizzas) {
            totalcost += pizza.getPrice();
        }
        model.addAttribute("allroles", roleRepository.findAll());
        model.addAttribute("allusers", userRepository.findAll());
        model.addAttribute("totalcost", totalcost);
        model.addAttribute("toptoppings", toppingRepository
                .findTop3ByCountIsNotNullOrderByCountDesc());
        return "admin";
    }
}
