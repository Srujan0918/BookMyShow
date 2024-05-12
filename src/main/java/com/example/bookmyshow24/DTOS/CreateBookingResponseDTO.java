package com.example.bookmyshow24.DTOS;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateBookingResponseDTO {
    private Long Bookingid;
    private ResponseStatus responseStatus;
}
