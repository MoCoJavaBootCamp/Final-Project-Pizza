package com.example.demo.tables;

import com.sun.istack.NotNull;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
public class Pizza {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private double price;

    private LocalDateTime date;

    @NotNull
    @NotEmpty
    private String sauce;

    @NotNull
    @NotEmpty
    private String name;

    @ManyToMany
    private Set<Topping> toppings;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    //Constructors
    public Pizza() {
        this.price = 10.25;
    }

    //Getters and Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getPrice() {
        int toppings = this.findNumToppings();

        if (toppings > 2) {
            for (int i = 2; i < toppings; i++) {
                price += .5;
            }
        }

        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getSauce() {
        return sauce;
    }

    public void setSauce(String sauce) {
        this.sauce = sauce;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Topping> getToppings() {
        return toppings;
    }

    public void setToppings(Set<Topping> toppings) {
        this.toppings = toppings;
    }

    //methods
    public int findNumToppings() {
        return toppings.size();
    }


}
