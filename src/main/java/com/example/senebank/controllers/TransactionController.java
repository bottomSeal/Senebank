package com.example.senebank.controllers;

import com.example.senebank.messages.requests.TransactionRegisterRequest;
import com.example.senebank.messages.responses.TransactionRegisterResponse;
import com.example.senebank.models.TransactionModel;
import com.example.senebank.services.TransactionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/transaction")
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;

    @PostMapping("/exchange")
    public TransactionRegisterResponse exchange(@RequestBody TransactionRegisterRequest registerRequest){
       log.info(registerRequest.toString());

        TransactionModel transactionModel = transactionService.exchange(registerRequest);

        return new TransactionRegisterResponse(
                transactionModel.getAccountFromId(),
                transactionModel.getAccountToId(),
                transactionModel.getPayload()
        );
    }
}
