package com.milekj.bookingdotmock.service;

import com.milekj.bookingdotmock.entity.User;
import com.milekj.bookingdotmock.repository.UserDTO;
import com.milekj.bookingdotmock.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    private UserRepository userRepository;
    private PasswordEncoder encoder;

    @Transactional
    public void save(UserDTO userDTO) {
        User user = toUser(userDTO);
        String encodedPassword = encoder.encode(user.getPassword());
        System.out.println(encodedPassword.length());
        user.setPassword(encodedPassword);
        userRepository.save(user);
    }

    private User toUser(UserDTO userDTO) {
            return new User(userDTO.getUsername(),
                    userDTO.getPassword(),
                    true,
                    userDTO.getEmail());
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setEncoder(PasswordEncoder encoder) {
        this.encoder = encoder;
    }
}
