package com.example.bookmyshow24.Repositories;

import com.example.bookmyshow24.Models.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Booking,Long> {

    @Override
    Booking save(Booking booking);

}
