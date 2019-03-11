package com.milekj.bookingdotmock.service;

import com.milekj.bookingdotmock.entity.Owner;
import com.milekj.bookingdotmock.entity.User;
import com.milekj.bookingdotmock.entity.UserRole;
import com.milekj.bookingdotmock.entity.UserRolePK;
import com.milekj.bookingdotmock.repository.OwnerRepository;
import com.milekj.bookingdotmock.repository.RegistrationFormDTO;
import com.milekj.bookingdotmock.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.milekj.bookingdotmock.service.RegistrationUtilities.*;

@Service
public class RegistrationService {
    @Value("${authorities.owner}")
    private String OWNER_AUTHORITY;

    @Value("${authorities.customer}")
    private String CUSTOMER_AUTHORITY;

    private UserRepository userRepository;
    private OwnerRepository ownerRepository;

    @Transactional
    public void addNewUser(RegistrationFormDTO formDTO) {
        User user = toUser(formDTO);
        addAuthorityAndEncodePassword(user, CUSTOMER_AUTHORITY);
        userRepository.save(user);
    }

    @Transactional
    public void addNewOwner(RegistrationFormDTO formDTO) {
        Owner owner = toOwner(formDTO);
        addAuthorityAndEncodePassword(owner, OWNER_AUTHORITY);
        ownerRepository.save(owner);
    }

    private void addAuthorityAndEncodePassword(User user, String authority) {
        encodeUserPassword(user);
        UserRolePK rolePK = new UserRolePK(user.getUsername(), authority);
        UserRole role = new UserRole(rolePK);
        user.addRole(role);
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setOwnerRepository(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }
}
