package com.example.senebank.dao;

import com.example.senebank.entities.UserEntity;
import com.example.senebank.models.enums.UserRole;
import com.example.senebank.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.UUID;


@Component
@RequiredArgsConstructor
public class UserDao {

    private final UserRepository userRepository;

    public boolean isUserExist(String email){
        return userRepository.findByEmail(email).isPresent();
    }

    public UUID getUserIdByEmail(String email){
        return userRepository.findByEmail(email).orElseThrow().getUserId();
    }

    public String getEmailById(UUID userId){
        return userRepository.findById(userId).orElseThrow().getEmail();
    }

    @Transactional
    public UserEntity createUser(String email, String password, UserRole role){

        return userRepository.save(UserEntity.builder()
                .email(email)
                .password(password)
                .registerDate(LocalDate.now())
                .userRole(role)
                .build());

    }

    public UserEntity getUserByEmail(String email){
        return userRepository.findByEmail(email).orElseThrow();
    }

    public String getPasswordHash(String email){
        return userRepository.findByEmail(email).orElseThrow().getPassword();
    }

    @Transactional
    public void deleteById(UUID userId){
        userRepository.deleteById(userId);
    }
}
