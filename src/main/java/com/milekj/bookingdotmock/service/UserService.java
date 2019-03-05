package com.milekj.bookingdotmock.service;

import com.milekj.bookingdotmock.entity.User;
import com.milekj.bookingdotmock.repository.RegistrationFormDTO;
import com.milekj.bookingdotmock.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
