package com.example.bookmyshow24.Controllers;

import com.example.bookmyshow24.DTOS.*;
import com.example.bookmyshow24.Exceptions.UserNotFoundException;
import com.example.bookmyshow24.Models.User;
import com.example.bookmyshow24.Services.UserService;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {
    private UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }
    public SignupResponseDTO SignUP(SignUpRequestDTO requestDTO){
        User user = userService.SignUp(requestDTO.getName(),requestDTO.getEmail(),
                requestDTO.getPassword());
        SignupResponseDTO responseDTO = new SignupResponseDTO();
        responseDTO.setResponseStatus(ResponseStatus.SUCCESS);
        responseDTO.setUserid(user.getId());
        return responseDTO;
    }

    public LoginResponseDTO Login(LoginRequestDTO requestDTO){

        LoginResponseDTO responseDTO = new LoginResponseDTO();
        try {
            User user = userService.Login(requestDTO.getEmail(),requestDTO.getPassword());
            responseDTO.setResponseStatus(ResponseStatus.SUCCESS);
        } catch (Exception e) {
            responseDTO.setResponseStatus(ResponseStatus.FAILURE);
        }
        return responseDTO;
    }
}
