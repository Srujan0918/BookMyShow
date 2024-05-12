package com.example.bookmyshow24.Repositories;

import com.example.bookmyshow24.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    @Override
    Optional<User> findById(Long aLong);

    Optional<User> findByEmail(String email);
}
