package com.example.bookmyshow24.Services;

import com.example.bookmyshow24.Models.Show;
import com.example.bookmyshow24.Models.ShowSeat;
import com.example.bookmyshow24.Models.ShowseatType;
import com.example.bookmyshow24.Repositories.ShowSeatTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PriceCalculator {

    private ShowSeatTypeRepository showSeatTypeRepository;

    public PriceCalculator(ShowSeatTypeRepository showSeatTypeRepository) {
        this.showSeatTypeRepository = showSeatTypeRepository;
    }
    public int calculateprice(Show show, List<ShowSeat> showSeats){
        int Amount=0;
        List<ShowseatType> showseatTypes = showSeatTypeRepository.findAllByShow(show);

        for(ShowSeat showSeat : showSeats){
            for(ShowseatType showseatType : showseatTypes){
                if(showSeat.getSeat().getSeatType().equals(showseatType.getSeatType())){
                    Amount += showseatType.getPrice();
                    break;
                }
            }
        }
        return Amount;
    }
}
