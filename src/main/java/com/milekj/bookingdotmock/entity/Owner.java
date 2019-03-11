package com.milekj.bookingdotmock.entity;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "owners")
@Inheritance(strategy = InheritanceType.JOINED)
@PrimaryKeyJoinColumn(name = "username")
public class Owner extends User {
    @OneToMany(mappedBy = "owner")
    private List<Hotel> hotels;

    public Owner() {
        hotels = new LinkedList<>();
    }

    public Owner(String username, String password, boolean enabled, String email) {
        super(username, password, enabled, email);
    }

    public void addToHotels(Hotel hotel) {
        hotels.add(hotel);
    }

    public void addHotel(Hotel hotel) {
        addToHotels(hotel);
        hotel.setOwner(this);
    }

    public List<Hotel> getHotels() {
        return hotels;
    }

    public void setHotels(List<Hotel> hotels) {
        this.hotels = hotels;
    }
}
