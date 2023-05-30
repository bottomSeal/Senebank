package com.example.senebank.controllers;

import com.example.senebank.messages.requests.UserLoginRequest;
import com.example.senebank.messages.requests.UserRegisterRequest;
import com.example.senebank.messages.responses.UserDeleteResponse;
import com.example.senebank.messages.responses.UserLoginResponse;
import com.example.senebank.messages.responses.UserRegisterResponse;
import com.example.senebank.models.TokenModel;
import com.example.senebank.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/auth")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public UserRegisterResponse register(@RequestBody UserRegisterRequest registerRequest){
        log.info(registerRequest.toString());

        TokenModel tokenModel = userService.register(registerRequest);

        return new UserRegisterResponse(
                tokenModel.getEmail(),
                tokenModel.getUserId(),
                tokenModel.getToken()
        );

    }

    @PostMapping("/login")
    public UserLoginResponse login(@RequestBody UserLoginRequest loginRequest){
        log.info(loginRequest.toString());

        TokenModel tokenModel = userService.login(loginRequest);

        return new UserLoginResponse(tokenModel.getEmail(), tokenModel.getToken());

    }

    @DeleteMapping("/delete")
    public UserDeleteResponse delete(){

        return new UserDeleteResponse(userService.deleteUser());

    }
}
