package com.milekj.bookingdotmock.entity;

import javax.persistence.*;

@Entity
@Table(name = "customers")
@Inheritance(strategy = InheritanceType.JOINED)
@PrimaryKeyJoinColumn(name = "username")
public class Customer extends User{
    public Customer() {
    }

    public Customer(String username, String password, boolean enabled, String email) {
        super(username, password, enabled, email);
    }
}
