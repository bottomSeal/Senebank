package com.example.senebank.controllers;

import com.example.senebank.messages.requests.AdminGetAccountTransactionRequest;
import com.example.senebank.messages.requests.AdminGetUserTransactionRequest;
import com.example.senebank.messages.responses.AllTransactionResponse;
import com.example.senebank.services.AdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;

    @GetMapping("/all_transactions")
    public AllTransactionResponse getAllTransaction() {

        return new AllTransactionResponse(adminService.getAllTransaction());

    }

    @GetMapping("/all_user_transactions")
    public AllTransactionResponse getAllUserTransaction(@RequestBody AdminGetUserTransactionRequest request) {
        log.info(request.toString());

        return new AllTransactionResponse(adminService.getAllUserTransaction(request));

    }

    @GetMapping("/all_account_transactions")
    public AllTransactionResponse getAllAccountTransaction(@RequestBody AdminGetAccountTransactionRequest request) {
        log.info(request.toString());

        return new AllTransactionResponse(adminService.getAllAccountTransaction(request));

    }
}
