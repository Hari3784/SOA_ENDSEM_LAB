package com.example.tourist.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "tourists")
public class Tourist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private Integer age;
    private String email;
    private String phone;
    private String city;
    private String country;

    public Tourist() {}

    public Tourist(String name, Integer age, String email, String phone, String city, String country) {
        this.name = name;
        this.age = age;
        this.email = email;
        this.phone = phone;
        this.city = city;
        this.country = country;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Integer getAge() { return age; }
    public void setAge(Integer age) { this.age = age; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }
    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }
}
