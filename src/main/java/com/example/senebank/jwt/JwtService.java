package com.example.senebank.jwt;

import com.example.senebank.models.UserModel;

public interface JwtService {

    String generateToken(UserModel userModel);

    UserModel parseToken(String jwt);

}
