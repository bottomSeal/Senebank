package com.example.senebank.services;

import com.example.senebank.messages.requests.AccountClosureRequest;
import com.example.senebank.messages.requests.AccountInfoRequest;
import com.example.senebank.messages.requests.AccountRegisterRequest;
import com.example.senebank.models.AccountModel;

public interface AccountService {

    AccountModel register(AccountRegisterRequest accountRegisterRequest);

    AccountModel closure(AccountClosureRequest accountClosureRequest);

    AccountModel info(AccountInfoRequest accountInfoRequest);

}
