package com.example.bookmyshow24.Services;

import com.example.bookmyshow24.Exceptions.UserNotFoundException;
import com.example.bookmyshow24.Models.*;
import com.example.bookmyshow24.Repositories.BookingRepository;
import com.example.bookmyshow24.Repositories.ShowRepository;
import com.example.bookmyshow24.Repositories.ShowseatRepository;
import com.example.bookmyshow24.Repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {
    private ShowseatRepository showseatRepository;
    private UserRepository userRepository;
    private ShowRepository showRepository;
    private PriceCalculator priceCalculator;
    private BookingRepository bookingRepository;
    public BookingService(UserRepository userRepository, ShowRepository showRepository,
                          ShowseatRepository showseatRepository, PriceCalculator priceCalculator,
                          BookingRepository bookingRepository){
        this.userRepository = userRepository;
        this.showRepository = showRepository;
        this.showseatRepository = showseatRepository;
        this.priceCalculator = priceCalculator;
        this.bookingRepository = bookingRepository;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Booking CreateBooking(Long Userid, List<Long> Showseatid,Long showID)
            throws UserNotFoundException {

        Optional<User> optionalUser = userRepository.findById(Userid);

        User user = optionalUser.get();

        if(optionalUser.isEmpty()){
            throw new UserNotFoundException("Userid "+Userid+" not found");
        }

        Optional<Show> optionalShow = showRepository.findById(showID);

        Show show = optionalShow.get();

        if(optionalShow.isEmpty()){
            throw new RuntimeException("Showid "+showID+" not found");
        }

        List<ShowSeat> Showseats = showseatRepository.findAllById(Showseatid);

        for(ShowSeat showSeat : Showseats){
            if(!showSeat.getShowseatstatus().equals(Showseatstatus.AVAILABLE)){
                throw new RuntimeException("Showseat "+showSeat.getShowseatstatus()+" is not available");
            }
        }

        for(ShowSeat showSeat : Showseats){
            showSeat.setShowseatstatus(Showseatstatus.BLOCKED);
            showseatRepository.save(showSeat);
        }


        Booking booking = new Booking();
        booking.setBookingStatus(BookingStatus.Pending);
        booking.setCreatedat(new Date());
        booking.setUser(user);
        booking.setShow(show);
        booking.setPayments(new ArrayList<>());
        booking.setShowSeats(Showseats);
        booking.setAmount(priceCalculator.calculateprice(show,Showseats));


        return bookingRepository.save(booking);
    }
}
