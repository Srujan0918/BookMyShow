package com.example.bookmyshow24.Controllers;

import com.example.bookmyshow24.DTOS.CreateBookingRequestDTO;
import com.example.bookmyshow24.DTOS.CreateBookingResponseDTO;
import com.example.bookmyshow24.DTOS.ResponseStatus;
import com.example.bookmyshow24.Exceptions.UserNotFoundException;
import com.example.bookmyshow24.Models.Booking;
import com.example.bookmyshow24.Services.BookingService;
import org.springframework.stereotype.Controller;

@Controller
public class BookingController {
    private BookingService bookingService;

    public BookingController(BookingService bookingService){
        this.bookingService = bookingService;
    }
    private CreateBookingResponseDTO BookingTicket(CreateBookingRequestDTO RequestDTO){

        CreateBookingResponseDTO ResponseDTO = new CreateBookingResponseDTO();

        try {
            Booking booking = bookingService.CreateBooking(RequestDTO.getUserid(),
                    RequestDTO.getShowseatid(),
                    RequestDTO.getShowid());

            ResponseDTO.setBookingid(booking.getId());
            ResponseDTO.setResponseStatus(ResponseStatus.SUCCESS);
        } catch (Exception e) {
            ResponseDTO.setResponseStatus(ResponseStatus.FAILURE);
        }

        return ResponseDTO;
    }
}
