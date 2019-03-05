package com.milekj.bookingdotmock.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "owners")
@Inheritance(strategy = InheritanceType.JOINED)
@PrimaryKeyJoinColumn(name = "username")
public class Owner extends User {
    @OneToMany(mappedBy = "owner")
    private List<Hotel> hotels;

    public Owner() {
    }

    public Owner(String username, String password, boolean enabled, String email) {
        super(username, password, enabled, email);
    }
}
