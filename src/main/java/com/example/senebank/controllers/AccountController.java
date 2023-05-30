package com.example.senebank.controllers;

import com.example.senebank.messages.requests.AccountClosureRequest;
import com.example.senebank.messages.requests.AccountInfoRequest;
import com.example.senebank.messages.requests.AccountRegisterRequest;
import com.example.senebank.messages.responses.AccountClosureResponse;
import com.example.senebank.messages.responses.AccountInfoResponse;
import com.example.senebank.messages.responses.AccountRegisterResponse;
import com.example.senebank.models.AccountModel;
import com.example.senebank.services.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @PostMapping("/register")
    public AccountRegisterResponse register(@RequestBody AccountRegisterRequest registerRequest){
        log.info(registerRequest.toString());

        AccountModel accountModel = accountService.register(registerRequest);

        return new AccountRegisterResponse(accountModel.getAccountId());
    }

    @DeleteMapping("/delete")
    public AccountClosureResponse closureAccount(@RequestBody AccountClosureRequest closureRequest){
        log.info(closureRequest.toString());

        AccountModel accountModel = accountService.closure(closureRequest);

        return new AccountClosureResponse(accountModel.getAccountId());
    }

    @GetMapping("/info")
    public AccountInfoResponse info(@RequestBody AccountInfoRequest infoRequest){
        log.info(infoRequest.toString());

        AccountModel accountModel = accountService.info(infoRequest);

        return new AccountInfoResponse(
                accountModel.getAccountId(),
                accountModel.getBalance(),
                accountModel.isOverdraft(),
                accountModel.getEmailOwner());
    }
}
