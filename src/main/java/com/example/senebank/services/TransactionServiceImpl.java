package com.example.senebank.services;

import com.example.senebank.messages.requests.TransactionRegisterRequest;
import com.example.senebank.models.AccountModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService{

    @Override
    public AccountModel exchange(TransactionRegisterRequest registerRequest) {



        return null;
    }
}
