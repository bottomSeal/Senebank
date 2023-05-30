package com.example.senebank;

import com.example.senebank.entities.UserEntity;
import com.example.senebank.models.enums.UserRole;
import com.example.senebank.repositories.AccountRepository;
import com.example.senebank.repositories.TransactionRepository;
import com.example.senebank.repositories.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataInitializer {

//    private final UserRepository userRepository;
//
//    private final PasswordEncoder passwordEncoder;

//    @PostConstruct
//    private void generate(){
//        log.info("DataInitializer post construct");
//        createAdmin();
//    }

//    private void createAdmin(){
//        userRepository.save(
//                UserEntity.builder()
//                        .email("admin@gmail.com")
//                        .password(passwordEncoder.encode("password"))
//                        .userRole(UserRole.ADMIN)
//                        .registerDate(LocalDate.now()).build()
//        );
//    }
}
