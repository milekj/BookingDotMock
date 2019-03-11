package com.milekj.bookingdotmock.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "hotels",
        uniqueConstraints = @UniqueConstraint(columnNames = {"name", "city", "address"}))
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Name cannot be empty")
    private String name;

    @NotEmpty(message = "City cannot be empty")
    private String city;

    @NotEmpty(message = "Address cannot be empty")
    private String address;

    @ManyToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "owner")
    private Owner owner;

    public Hotel() {
    }

    public Hotel(String name, String city, String Address) {
        this.name = name;
        this.city = city;
        this.address = Address;
    }

    public void addOwner(Owner owner) {
        this.owner = owner;
        owner.addToHotels(this);
    }

    public int getId() {
        return id;
    }
    public void setOwner(Owner owner) {
        this.owner = owner;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Owner getOwner() {
        return owner;
    }

}
