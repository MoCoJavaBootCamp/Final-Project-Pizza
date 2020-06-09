package com.example.demo;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Set;

@Entity
@Table(name="reports")
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name="total_sales")
    private double totalSales;

    @Column(name="date")
    private LocalDateTime date;

    @ManyToMany
    private Set<User> users;

    @ManyToMany
    private Set<Topping> topToppings;

    //Constructors
    public Report(){}

    //Getters & Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getTotalSales() {
        return totalSales;
    }

    public void setTotalSales(double totalSales) {
        this.totalSales = totalSales;
    }

    public LocalDateTime getDate() { return date; }

    public void setDate(LocalDateTime date) { this.date = date; }

    public Set<User> getUsers() { return users; }

    public void setUsers(Set<User> users) { this.users = users; }

    public Set<Topping> getTopToppings() {
        return topToppings;
    }

    public void setTopToppings(Set<Topping> topToppings) {
        this.topToppings = topToppings;
    }

    //Report Generation Options

    //list all customers
    //list customer by name
    //list top 3 toppings
    //total sales

}
