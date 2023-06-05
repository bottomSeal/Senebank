package com.example.senebank.controllers;

import com.example.senebank.controllers.AccountController;
import com.example.senebank.messages.requests.*;
import com.example.senebank.messages.responses.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;



@SpringBootTest
@Slf4j
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AccountControllerTest {

    @Autowired
    private AccountController accountController;

    @BeforeAll
    static void loadContext(){
        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(
                        "test1@mail.ru", null, null)
        );
    }

    @Test
    @Order(1)
    void accountIsRegister(){
        AccountRegisterRequest accountRegisterRequest = new AccountRegisterRequest(100, true);
        AccountRegisterResponse accountRegisterResponse = accountController.register(accountRegisterRequest);

        log.info("Register response: {}", accountRegisterResponse);

        Assertions.assertNotNull(accountRegisterResponse);

        Integer accountId = accountRegisterResponse.getAccountId();

        Assertions.assertNotNull(accountId);
    }

    @Test
    @Order(2)
    void accountGetInfoIsSuccess(){
        AccountInfoRequest accountInfoRequest = new AccountInfoRequest(7);
        AccountInfoResponse accountInfoResponse = accountController.info(accountInfoRequest);

        log.info("Account info response: {}", accountInfoResponse);

        Assertions.assertNotNull(accountInfoResponse);

        Integer balance = accountInfoResponse.getBalance();

        Assertions.assertNotNull(balance);

        Assertions.assertNotNull(accountInfoResponse.getEmailOwner());

        Assertions.assertEquals(accountInfoRequest.getAccountId(), accountInfoResponse.getAccountId());
    }

    @Test
    @Order(3)
    void accountIsClosure(){
        AccountClosureRequest accountClosureRequest = new AccountClosureRequest(24);
        AccountClosureResponse accountClosureResponse = accountController.closureAccount(accountClosureRequest);

        log.info("Account closure response: {}", accountClosureResponse);

        Assertions.assertNotNull(accountClosureResponse);

        Assertions.assertEquals(accountClosureRequest.getAccountId(), accountClosureResponse.getAccountId());
    }
}
