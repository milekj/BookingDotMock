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
public class HotelServiceImpl implements HotelService {
    private HotelRepository hotelRepository;
    private OwnerRepository ownerRepository;

    @Override
    @Transactional
    public void saveIfOwned(Hotel hotel, String username) {
        Owner owner = ownerRepository
                .findById(username)
                .orElseThrow(() -> new ResourceRestrictedOrNotExistingException("User is not an owner"));
        owner.addHotel(hotel);
        hotelRepository.save(hotel);
    }

    @Override
    @Transactional
    public void updateIfOwned(Hotel hotel, String username) {
        Owner owner = getHotelOwnerIfOwned(hotel.getId(), username);
        hotel.setOwner(owner);
        hotelRepository.save(hotel);
    }

    @Override
    @Transactional
    public void deleteById(long hotelId, String username) {
        getHotelOwnerIfOwned(hotelId, username);
        hotelRepository.deleteById(hotelId);
    }

    @Override
    @Transactional(readOnly = true)
    public Hotel findByIdIfOwned(long id, String username) {
        return hotelRepository
                .findByIdAndOwnerUsername(id, username)
                .orElseThrow(() -> new ResourceRestrictedOrNotExistingException("Hotel with given id does not exist or is not owned by user"));
    }


    @Override
    @Transactional(readOnly = true)
    public List<Hotel> findByOwnerUsername(String username) {
        return hotelRepository.getAllByOwnerUsername(username);
    }

    private Owner getHotelOwnerIfOwned(long hotelId, String username) {
        return ownerRepository
                .findByUsernameAndHotelsId(username, hotelId)
                .orElseThrow(() -> new ResourceRestrictedOrNotExistingException("Hotel is not owned by the user"));
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