package com.example.senebank.controllers;

import com.example.senebank.controllers.AdminController;
import com.example.senebank.dao.UserDao;
import com.example.senebank.messages.requests.AdminGetAccountTransactionRequest;
import com.example.senebank.messages.requests.AdminGetUserTransactionRequest;
import com.example.senebank.messages.responses.AllTransactionResponse;
import com.example.senebank.models.UserModel;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

@SpringBootTest
@Slf4j
class AdminControllerTest {

    @Autowired
    private AdminController adminController;

    @Autowired
    private UserDao userDao;

    @BeforeEach
    void loadContext(){
        UserModel admin = UserModel.fromEntity(userDao.getUserByEmail("admin@gmail.com"));

        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(
                        admin.getEmail(), null, admin.getAuthorities())
        );
    }

    @Test
    void getAllTransactionIsSuccess(){
        AllTransactionResponse allTransactionResponse = adminController.getAllTransaction();

        log.info("Get all transaction response: {}", allTransactionResponse);

        Assertions.assertNotNull(allTransactionResponse);
    }

    @Test
    void getAllUserTransactionIsSuccess(){
        AdminGetUserTransactionRequest adminGetUserTransactionRequest =
                new AdminGetUserTransactionRequest("test1@mail.ru");
        AllTransactionResponse allUserTransactionResponse = adminController
                .getAllUserTransaction(adminGetUserTransactionRequest);

        log.info("Get all user transaction response: {}", allUserTransactionResponse);

        Assertions.assertNotNull(allUserTransactionResponse);
    }

    @Test
    void getAllAccountTransactionIsSuccess(){
        AdminGetAccountTransactionRequest adminGetAccountTransactionRequest =
                new AdminGetAccountTransactionRequest(7);
        AllTransactionResponse allAccountTransactionResponse = adminController
                .getAllAccountTransaction(adminGetAccountTransactionRequest);

        log.info("Get all account transaction response: {}", allAccountTransactionResponse);

        Assertions.assertNotNull(allAccountTransactionResponse);
    }
}
