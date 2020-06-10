package com.example.demo;

import com.example.demo.repository.*;
import com.example.demo.tables.Pizza;
import com.example.demo.tables.Role;
import com.example.demo.tables.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.security.Principal;
import java.util.HashSet;
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
        pizzaRepository.save(confirmPizza);
        String username = principal.getName();
        userRepository.findByUsername(username).pizzas.add(confirmPizza);
        return "redirect:/";
    }

    @GetMapping("/menu")
    public String menu(Model model, Principal principal) {
        String username = principal.getName();
        model.addAttribute("specialtypizzas",
                pizzaRepository.findPizzasBySpecialtyTrue());
        model.addAttribute("spPizza", new Pizza());
        model.addAttribute("user", userRepository.findByUsername(username));
        return "menu";
    }

    @PostMapping("/menu")
    public String processMenu(@ModelAttribute("spPizza") Pizza pizza) {
        confirmPizza = pizza;
        System.out.println(confirmPizza.toString());
        return "redirect:/checkout";
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
