package com.example.demo;

import com.sun.istack.NotNull;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name="users_db")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    @Size(min = 2)
    @Column(name="username")
    private String username;

    @NotNull
    @Size(min = 2)
    @Column(name="email")
    private String email;

    @NotNull
    @Size(min = 2)
    @Column(name="password")
    private String password;

    @NotNull
    @Size(min = 2)
    @Column(name="first_name")
    private String firstName;

    @NotNull
    @Size(min = 2)
    @Column(name="last_name")
    private String lastName;

    @NotNull
    @Min(5)
    @Column(name="phone")
    private long phoneNumber;

    @NotNull
    @Size(min = 2)
    @Column(name = "street")
    private String street;

    @NotNull
    @Size(min = 2)
    @Column(name = "city")
    private String city;

    @NotNull
    @Min(5)
    @Column(name = "zip")
    private int zip;

    @Column (name = "enabled")
    private boolean enabled;

//    @Column(name = "orders")
//    private long orders;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL,
    fetch = FetchType.EAGER)
    public Set<Pizza> pizza;

    public User(){}

    public User(String username, String email, String password, String firstName,
                String lastName, long phoneNumber,
                String street, String city, int zip, boolean enabled) {
        this.username = username;
        this.email = email;
        setPassword(password);
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.street = street;
        this.city = city;
        this.zip = zip;
        this.enabled = enabled;
    }

    public User(String username, String email, String password, String firstName, String lastName,
                long phoneNumber, String street, String city, int zip, Set<Pizza> pizza, boolean enabled) {
        this.username = username;
        this.email = email;
        setPassword(password);
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.street = street;
        this.city = city;
        this.zip = zip;
        this.pizza = pizza;
        this.enabled = enabled;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        this.password = passwordEncoder.encode(password);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getZip() {
        return zip;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }

    public Set<Pizza> getPizza() {
        return pizza;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void setPizza(Set<Pizza> pizza) {
        this.pizza = pizza;
    }
}
