package com.example.senebank.services;

import com.example.senebank.dao.UserDao;
import com.example.senebank.entities.UserEntity;
import com.example.senebank.jwt.JwtService;
import com.example.senebank.messages.requests.UserLoginRequest;
import com.example.senebank.messages.requests.UserRegisterRequest;
import com.example.senebank.models.TokenModel;
import com.example.senebank.models.UserModel;
import com.example.senebank.models.enums.UserRole;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserDao userDao;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    @Override
    public TokenModel register(UserRegisterRequest registerRequest) {

        if(userDao.isUserExist(registerRequest.getEmail())) {
            log.info("User with same email already exist");
            throw new IllegalArgumentException("User with same email already exist");
        }

        UserEntity newUser = userDao.createUser(
                registerRequest.getEmail(),
                passwordEncoder.encode(registerRequest.getPassword()),
                UserRole.USER
        );


        String jwt = jwtService.generateToken(UserModel.fromEntity(newUser));

        return new TokenModel(
                newUser.getEmail(),
                jwt,
                newUser.getUserId()
        );
    }

    @Override
    public TokenModel login(UserLoginRequest loginRequest) {

        if(!userDao.isUserExist(loginRequest.getEmail())){
            log.info("User not found");
            throw new UsernameNotFoundException("User not found");
        }

        if(!passwordEncoder.matches(loginRequest.getPassword(), userDao.getPasswordHash(loginRequest.getEmail()))){
            throw new UsernameNotFoundException("Password not match");
        }

        UserModel user = UserModel.fromEntity(userDao.getUserByEmail(loginRequest.getEmail()));

        return new TokenModel(user.getEmail(), jwtService.generateToken(user), null);
    }

    @Override
    public String deleteUser() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        UserEntity user = userDao.getUserByEmail(authentication.getName());

        userDao.deleteById(user.getUserId());

        return user.getEmail();
    }
}
