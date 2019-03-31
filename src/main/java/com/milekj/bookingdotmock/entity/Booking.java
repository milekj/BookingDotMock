package com.milekj.bookingdotmock.entity;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.LinkedList;
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

    @OneToMany
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @JoinColumn(name = "booking_id", nullable = false, updatable = false)
    private List<BookingDetails> details;

    public Booking() {
        details = new LinkedList<>();
    }

    public Booking(Customer customer) {
        this();
        this.customer = customer;
    }

    public void addDetails(BookingDetails details) {
        this.details.add(details);
    }

    public void addBookingDetails(BookingDetails details) {
        this.details.add(details);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<BookingDetails> getDetails() {
        return details;
    }

    public void setDetails(List<BookingDetails> details) {
        this.details = details;
    }
}
