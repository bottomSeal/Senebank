package com.example.senebank.dao;

import com.example.senebank.entities.AccountEntity;
import com.example.senebank.entities.TransactionEntity;
import com.example.senebank.entities.UserEntity;
import com.example.senebank.messages.requests.TransactionRegisterRequest;
import com.example.senebank.models.TransactionModel;
import com.example.senebank.repositories.TransactionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class TransactionDao {

    private final TransactionRepository transactionRepository;

    private final AccountDao accountDao;

    @Transactional
    public TransactionEntity createTransaction(TransactionRegisterRequest transactionRegisterRequest,
                                              UserEntity transactionOwner){
        AccountEntity accountFrom = accountDao
                .getAccountByIdAndOwner(transactionRegisterRequest.getAccountFromId(), transactionOwner);

        if(accountFrom.getBalance() < transactionRegisterRequest.getPayload()) {
            log.info("Недостаточно средств на счете.");
            throw new IllegalArgumentException("Недостаточно средств на счете.");
        }

        accountDao.setNewBalance(accountFrom.getAccountId(),
                accountFrom.getBalance() - transactionRegisterRequest.getPayload());

        accountDao.setNewBalance(transactionRegisterRequest.getAccountToId(),
                accountFrom.getBalance() + transactionRegisterRequest.getPayload());

        return transactionRepository.save(
                TransactionEntity.builder()
                        .transactionOwner(transactionOwner)
                        .accountFromId(accountFrom)
                        .accountToId(accountDao.getAccountById(transactionRegisterRequest.getAccountToId()))
                        .payload(transactionRegisterRequest.getPayload())
                        .build()
        );
    }

    public TransactionEntity getTransactionById(int transactionId){
        return transactionRepository.findById(transactionId).orElseThrow();
    }

    public AccountEntity getTransactionByIdAndOwner(int transactionId, UserEntity transactionOwner){
        return transactionRepository
                .findByTransactionIdAndTransactionOwner(transactionId, transactionOwner)
                .orElseThrow();
    }

    public List<TransactionModel> getAllTransactions(){
        ArrayList<TransactionModel> list = new ArrayList<>();

        for (TransactionEntity transaction: transactionRepository.findAll()){
            list.add(TransactionModel.fromEntity(transaction));
        }

        return list;
    }

    public List<TransactionModel> getAllTransactionsByOwner(UserEntity transactionOwner){
        ArrayList<TransactionModel> list = new ArrayList<>();

        for (TransactionEntity transaction: transactionRepository.findAllByTransactionOwner(transactionOwner)){
            list.add(TransactionModel.fromEntity(transaction));
        }

        return list;
    }

    @Transactional
    public void deleteById(int transactionId) {
        transactionRepository.deleteById(transactionId);
    }

    @Transactional
    public void deleteByIdAndOwner(int transactionId, UserEntity transactionOwner) {
        transactionRepository
                .deleteByTransactionIdAndTransactionOwner(transactionId, transactionOwner);
    }
}
