package com.milekj.bookingdotmock.service;

import com.milekj.bookingdotmock.entity.Customer;
import com.milekj.bookingdotmock.entity.Owner;
import com.milekj.bookingdotmock.entity.User;
import com.milekj.bookingdotmock.dto.RegistrationFormDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class RegistrationUtilities {
    private static PasswordEncoder encoder;

    public static Customer toCustomer(RegistrationFormDTO registrationFormDTO) {
        return new Customer(registrationFormDTO.getUsername(),
                registrationFormDTO.getPassword(),
                true,
                registrationFormDTO.getEmail());
    }

    public static Owner toOwner(RegistrationFormDTO registrationFormDTO) {
        return new Owner(registrationFormDTO.getUsername(),
                registrationFormDTO.getPassword(),
                true,
                registrationFormDTO.getEmail());
    }

    public static void encodeUserPassword(User user) {
        String encodedPassword = encoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
    }

    @Autowired
    public void setEncoder(PasswordEncoder encoder) {
        RegistrationUtilities.encoder = encoder;
    }
}
