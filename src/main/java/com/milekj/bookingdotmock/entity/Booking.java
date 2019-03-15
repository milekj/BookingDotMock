package com.milekj.bookingdotmock.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "bookings")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    private List<BookingDetails> details;
}
