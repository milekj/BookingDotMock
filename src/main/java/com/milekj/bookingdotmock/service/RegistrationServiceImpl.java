package com.milekj.bookingdotmock.service;

import com.milekj.bookingdotmock.entity.*;
import com.milekj.bookingdotmock.repository.CustomerRepository;
import com.milekj.bookingdotmock.repository.OwnerRepository;
import com.milekj.bookingdotmock.dto.RegistrationFormDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.milekj.bookingdotmock.service.RegistrationUtilities.*;

@Service
public class RegistrationServiceImpl implements RegistrationService {
    @Value("${authorities.owner}")
    private String OWNER_AUTHORITY;

    @Value("${authorities.customer}")
    private String CUSTOMER_AUTHORITY;

    private CustomerRepository customerRepository;
    private OwnerRepository ownerRepository;

    @Override
    @Transactional
    public void addNewCustomer(RegistrationFormDTO formDTO) {
        Customer customer = toCustomer(formDTO);
        addAuthorityAndEncodePassword(customer, CUSTOMER_AUTHORITY);
        customerRepository.save(customer);
    }

    @Override
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
    public void setCustomerRepository(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Autowired
    public void setOwnerRepository(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }
}
