package com.milekj.bookingdotmock.repository;

import com.milekj.bookingdotmock.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, String> {

}
