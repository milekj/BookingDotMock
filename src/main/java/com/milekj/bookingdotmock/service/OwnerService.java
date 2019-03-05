package com.milekj.bookingdotmock.service;

import com.milekj.bookingdotmock.entity.Owner;
import com.milekj.bookingdotmock.entity.UserRole;
import com.milekj.bookingdotmock.entity.UserRolePK;
import com.milekj.bookingdotmock.repository.OwnerRepository;
import com.milekj.bookingdotmock.repository.RegistrationFormDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OwnerService {
    private OwnerRepository ownerRepository;

    @Autowired
    public void setOwnerRepository(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }
}
