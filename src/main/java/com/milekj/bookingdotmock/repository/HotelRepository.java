package com.milekj.bookingdotmock.repository;

import com.milekj.bookingdotmock.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {
    List<Hotel> getAllByOwnerUsername(String username);
    Optional<Hotel> findByIdAndOwnerUsername(long id, String username);
    boolean existsByIdAndOwnerUsername(long id, String username);
}
