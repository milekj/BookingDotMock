package com.milekj.bookingdotmock.service;

import com.milekj.bookingdotmock.entity.Owner;
import com.milekj.bookingdotmock.entity.User;
import com.milekj.bookingdotmock.repository.RegistrationFormDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.net.URLEncoder;

@Component
public class RegistrationUtilities {
    public static final String OWNER_AUTHORITY = "OWNER";
    public static final String CUSTOMER_AUTHORITY = "CUSTOMER";

    private static PasswordEncoder encoder;

    public static User toUser(RegistrationFormDTO registrationFormDTO) {
        return new User(registrationFormDTO.getUsername(),
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
