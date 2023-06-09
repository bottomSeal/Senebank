package com.example.senebank.services;

import com.example.senebank.dao.TransactionDao;
import com.example.senebank.dao.UserDao;
import com.example.senebank.entities.TransactionEntity;
import com.example.senebank.messages.requests.TransactionRegisterRequest;
import com.example.senebank.models.TransactionModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService{

    private final TransactionDao transactionDao;

    private final UserDao userDao;

    @Override
    public TransactionModel exchange(TransactionRegisterRequest registerRequest) {

        TransactionEntity newTransaction = transactionDao.createTransaction(
                registerRequest,
                userDao.getUserByEmail(
                        (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal()
                )
        );

        return TransactionModel.fromEntity(newTransaction);
    }
}
