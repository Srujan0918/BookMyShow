package com.example.bookmyshow24.Services;

import com.example.bookmyshow24.Exceptions.UserNotFoundException;
import com.example.bookmyshow24.Models.User;
import com.example.bookmyshow24.Repositories.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public User SignUp(String name,String email, String password) {

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(bCryptPasswordEncoder.encode(password));

        return userRepository.save(user);
    }

    public User Login(String email, String password) throws UserNotFoundException {

        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException("User with email "+email+" not found");
        }
        User user = optionalUser.get();
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        if (bCryptPasswordEncoder.matches(password, user.getPassword())) {
            return user;
        }
        throw new RuntimeException("Wrong password");
    }
}
