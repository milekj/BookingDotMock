package com.milekj.bookingdotmock.service;

import com.milekj.bookingdotmock.dto.RegistrationFormDTO;
import org.springframework.transaction.annotation.Transactional;

public interface RegistrationService {
    void addNewCustomer(RegistrationFormDTO formDTO);
    void addNewOwner(RegistrationFormDTO formDTO);
}
