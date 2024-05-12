package com.example.bookmyshow24.Repositories;

import com.example.bookmyshow24.Models.ShowSeat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShowseatRepository extends JpaRepository<ShowSeat,Long> {
    @Override
    List<ShowSeat> findAllById(Iterable<Long> longs);

    //Update + Insert = Upsert
    @Override
    ShowSeat save(ShowSeat showSeat);

}
