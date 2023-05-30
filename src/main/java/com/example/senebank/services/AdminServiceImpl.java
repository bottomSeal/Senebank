package com.example.senebank.services;

import com.example.senebank.dao.AccountDao;
import com.example.senebank.dao.TransactionDao;
import com.example.senebank.dao.UserDao;import com.example.senebank.messages.requests.AdminGetAccountTransactionRequest;
import com.example.senebank.messages.requests.AdminGetUserTransactionRequest;
import com.example.senebank.models.TransactionModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService{

    private final AccountDao accountDao;

    private final TransactionDao transactionDao;

    private final UserDao userDao;

    @Override
    public List<TransactionModel> getAllTransaction() {
        return transactionDao.getAllTransactions();
    }

    @Override
    public List<TransactionModel> getAllUserTransaction(AdminGetUserTransactionRequest request) {
        return transactionDao.getAllTransactionsByOwner(userDao.getUserByEmail(request.getEmail()));
    }

    @Override
    public List<TransactionModel> getAllAccountTransaction(AdminGetAccountTransactionRequest request) {
        return transactionDao.getAllTransactionsByAccount(request.getAccountId());
    }
}
