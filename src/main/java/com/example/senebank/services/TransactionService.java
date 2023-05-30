package com.example.senebank.services;

import com.example.senebank.messages.requests.TransactionRegisterRequest;
import com.example.senebank.models.TransactionModel;

public interface TransactionService {

    TransactionModel exchange(TransactionRegisterRequest registerRequest);

}
