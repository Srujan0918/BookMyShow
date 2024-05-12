package com.example.bookmyshow24.Repositories;

import com.example.bookmyshow24.Models.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShowRepository extends JpaRepository<Show,Long> {
}
