package com.example.bookmyshow24.DTOS;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CreateBookingRequestDTO {
    private Long userid;
    private Long showid;
    private List<Long> showseatid;
}
