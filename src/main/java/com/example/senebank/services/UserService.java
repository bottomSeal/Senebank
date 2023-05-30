package com.example.senebank.services;

import com.example.senebank.messages.requests.UserLoginRequest;
import com.example.senebank.messages.requests.UserRegisterRequest;
import com.example.senebank.models.TokenModel;

public interface UserService {

    TokenModel register(UserRegisterRequest registerRequest);

    TokenModel login(UserLoginRequest loginRequest);

    String deleteUser();

}
