package com.example.senebank;

import com.example.senebank.repositories.AccountRepository;
import com.example.senebank.repositories.TransactionRepository;
import com.example.senebank.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataInitializer {

    private final UserRepository userRepository;

    private final AccountRepository accountRepository;

    private final TransactionRepository transactionRepository;
}
