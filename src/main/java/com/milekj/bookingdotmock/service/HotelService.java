package com.milekj.bookingdotmock.service;

import com.milekj.bookingdotmock.entity.Hotel;
import com.milekj.bookingdotmock.entity.Owner;
import com.milekj.bookingdotmock.exception.ResourceRestrictedOrNotExistingException;
import com.milekj.bookingdotmock.repository.HotelRepository;
import com.milekj.bookingdotmock.repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class HotelService {
    private HotelRepository hotelRepository;
    private OwnerRepository ownerRepository;

    @Transactional(readOnly = true)
    public Hotel findById(long hotelId) {
        return hotelRepository
                .findById(hotelId)
                .orElseThrow(() -> new ResourceRestrictedOrNotExistingException("Hotel with given id does not exist"));
    }

    @Transactional
    public void saveOrUpdate(Hotel hotel, String username) {
        Owner owner = ownerRepository
                .findById(username)
                .orElseThrow(() -> new SecurityException("User has no permission to add a hotel or does not exist"));
        //consider using @Secured
        owner.addHotel(hotel);
        hotelRepository.save(hotel);

    }

    @Transactional(readOnly = true)
    public List<Hotel> findByOwnerUsername(String username) {
        return hotelRepository.getAllByOwnerUsername(username);
        //add checking if username belongs to an owner
    }

    @Transactional(readOnly = true)
    public Hotel findByIdIfOwned(long id, String username) {
        return hotelRepository
                .findByIdAndOwnerUsername(id, username)
                .orElseThrow(() -> new ResourceRestrictedOrNotExistingException("Hotel with given id does not exist or is not owned by user"));
    }

    @Transactional
    public void deleteById(long hotelId) {
        hotelRepository.deleteById(hotelId);
    }

    @Autowired
    public void setHotelRepository(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    @Autowired
    public void setOwnerRepository(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }
}