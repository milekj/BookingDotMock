package com.milekj.bookingdotmock.service;

import com.milekj.bookingdotmock.entity.User;
import com.milekj.bookingdotmock.entity.UserRole;
import com.milekj.bookingdotmock.repository.UserRepository;
import com.milekj.bookingdotmock.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DbUserDetailsService implements UserDetailsService {
    private UserRepository userRepository;
    private UserRoleRepository userRoleRepository;

    @Override
    @Transactional(readOnly = true)
    public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository
                .findById(username)
                .orElseThrow(() -> new UsernameNotFoundException(username + " was not found"));
        List<UserRole> roles = user.getRoles();
        return new UserDetailsImpl(user, roles);
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setUserRoleRepository(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }
}
