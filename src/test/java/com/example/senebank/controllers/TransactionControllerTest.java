package com.example.senebank.controllers;

import com.example.senebank.controllers.AccountController;
import com.example.senebank.controllers.TransactionController;
import com.example.senebank.dao.AccountDao;
import com.example.senebank.messages.requests.AccountRegisterRequest;
import com.example.senebank.messages.requests.TransactionRegisterRequest;
import com.example.senebank.messages.responses.AccountRegisterResponse;
import com.example.senebank.messages.responses.TransactionRegisterResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

@SpringBootTest
@Slf4j
class TransactionControllerTest {

    @Autowired
    private TransactionController transactionController;

    @Autowired
    private AccountController accountController;

    @Autowired
    private AccountDao accountDao;

    @BeforeAll
    static void loadContext(){
        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(
                        "test1@mail.ru", null, null)
        );
    }

    @Test
    void isMoneyExchange(){
        AccountRegisterRequest accountRegisterRequest1 = new AccountRegisterRequest(1000, true);
        AccountRegisterResponse accountRegisterResponse1 = accountController.register(accountRegisterRequest1);

        int account1BalanceBefore = accountDao.getAccountById(accountRegisterResponse1.getAccountId()).getBalance();

        AccountRegisterRequest accountRegisterRequest2 = new AccountRegisterRequest(0, true);
        AccountRegisterResponse accountRegisterResponse2 = accountController.register(accountRegisterRequest2);

        int account2BalanceBefore = accountDao.getAccountById(accountRegisterResponse2.getAccountId()).getBalance();

        TransactionRegisterRequest transactionRegisterRequest = new TransactionRegisterRequest(100,
                accountRegisterResponse1.getAccountId(), accountRegisterResponse2.getAccountId());

        TransactionRegisterResponse transactionRegisterResponse = transactionController
                .exchange(transactionRegisterRequest);

        int account1BalanceAfter = accountDao.getAccountById(accountRegisterResponse1.getAccountId()).getBalance();
        int account2BalanceAfter = accountDao.getAccountById(accountRegisterResponse2.getAccountId()).getBalance();

        log.info("Register response: {}", transactionRegisterResponse);

        Assertions.assertNotNull(transactionRegisterResponse);

        Assertions.assertEquals(account1BalanceBefore,
                account1BalanceAfter + transactionRegisterRequest.getPayload());

        Assertions.assertEquals(account2BalanceBefore,
                account2BalanceAfter - transactionRegisterRequest.getPayload());

    }
}
