package com.milekj.bookingdotmock.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "hotels",
        uniqueConstraints = @UniqueConstraint(columnNames = {"name", "city", "address"}))
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotEmpty(message = "Name cannot be empty")
    private String name;

    @NotEmpty(message = "City cannot be empty")
    private String city;

    @NotEmpty(message = "Address cannot be empty")
    private String address;

    @ManyToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "owner")
    private Owner owner;

    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL)
    private List<Room> rooms;

    public Hotel() {
        rooms = new LinkedList<>();
    }

    public Hotel(String name, String city, String address) {
        this();
        this.name = name;
        this.city = city;
        this.address = address;
    }

    public void addOwner(Owner owner) {
        this.owner = owner;
        owner.addToHotels(this);
    }

    public void addToRooms(Room room) {
        rooms.add(room);
    }

    public void addRoom(Room room) {
        addToRooms(room);
        room.addHotel(this);
    }


    public Long getId() {
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

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }
}
