package com.milekj.bookingdotmock.entity;

import javax.persistence.*;

@Entity
@Table(name = "hotels",
        uniqueConstraints = @UniqueConstraint(columnNames = {"name", "city", "street"}))
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String city;

    private String street;

    @ManyToOne
    @JoinColumn(name = "owner")
    private Owner owner;

    public Hotel() {
    }

    public Hotel(String name, String city, String street) {
        this.name = name;
        this.city = city;
        this.street = street;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }
}
