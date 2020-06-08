package com.example.demo;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Set;

@Entity
@Table(name="reports")
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="user_id")
    private Set<Long> users;

    @Column(name="total_sales")
    private double totalSales;

    @OneToMany(mappedBy = "report",
            cascade = CascadeType.REMOVE,
            fetch = FetchType.EAGER)
    private ArrayList<Long> topToppings;

    public Report(){}

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

    public ArrayList<Long> getTopToppings() {
        return topToppings;
    }

    public void setTopToppings(ArrayList<Long> topToppings) {
        this.topToppings = topToppings;
    }
}
