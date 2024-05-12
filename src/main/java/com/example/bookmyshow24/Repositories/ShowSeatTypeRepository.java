package com.example.bookmyshow24.Repositories;

import com.example.bookmyshow24.Models.Show;
import com.example.bookmyshow24.Models.ShowseatType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShowSeatTypeRepository extends JpaRepository<ShowseatType,Long> {

    List<ShowseatType> findAllByShow(Show show);
}
