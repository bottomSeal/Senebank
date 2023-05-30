package com.example.senebank.services;

import com.example.senebank.messages.requests.TransactionRegisterRequest;
import com.example.senebank.models.AccountModel;

public interface TransactionService {

    AccountModel exchange(TransactionRegisterRequest registerRequest);

}
