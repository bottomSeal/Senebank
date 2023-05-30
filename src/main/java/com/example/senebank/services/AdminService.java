package com.example.senebank.services;

import com.example.senebank.messages.requests.AdminGetAccountTransactionRequest;
import com.example.senebank.messages.requests.AdminGetUserTransactionRequest;
import com.example.senebank.models.TransactionModel;

import java.util.List;

public interface AdminService {

    List<TransactionModel> getAllTransaction();

    List<TransactionModel> getAllUserTransaction(AdminGetUserTransactionRequest request);

    List<TransactionModel> getAllAccountTransaction(AdminGetAccountTransactionRequest request);

}
