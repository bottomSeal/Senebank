package com.example.senebank.dao;


import com.example.senebank.entities.AccountEntity;
import com.example.senebank.entities.UserEntity;
import com.example.senebank.messages.requests.AccountRegisterRequest;
import com.example.senebank.models.AccountModel;
import com.example.senebank.repositories.AccountRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class AccountDao {

    private final AccountRepository accountRepository;

    @Transactional
    public AccountEntity createAccount(AccountRegisterRequest accountRegisterRequest, UserEntity accountOwner){
        return accountRepository.save(
                AccountEntity.builder()
                        .balance(accountRegisterRequest.getBalance())
                        .isOverdraft(accountRegisterRequest.isOverdraft())
                        .accountOwner(accountOwner)
                        .transferredTransaction(null)
                        .receivedTransaction(null)
                        .build()
        );
    }

    public AccountEntity getAccountById(int accountId){
        return accountRepository.findById(accountId).orElseThrow();
    }

    public AccountEntity getAccountByIdAndOwner(int accountId, UserEntity accountOwner){
        return accountRepository.findByAccountIdAndAccountOwner(accountId, accountOwner).orElseThrow();
    }

    public List<AccountModel> getAllAccounts(){
        ArrayList<AccountModel> list = new ArrayList<>();

        for (AccountEntity account: accountRepository.findAll()){
            list.add(AccountModel.fromEntity(account));
        }

        return list;
    }

    public List<AccountModel> getAllAccountsByOwner(UserEntity accountOwner){
        ArrayList<AccountModel> list = new ArrayList<>();

        for (AccountEntity account: accountRepository.findAllByAccountOwner(accountOwner)){
            list.add(AccountModel.fromEntity(account));
        }

        return list;
    }

    @Transactional
    public void deleteById(int accountId) {
        accountRepository.deleteById(accountId);
    }

    @Transactional
    public void deleteByIdAndOwner(int accountId, UserEntity accountOwner) {
        accountRepository
                .deleteByAccountIdAndAccountOwner(accountId, accountOwner);
    }

    @Transactional
    public void setNewBalance(int accountId, int newBalance) {
        accountRepository.setBalance(accountId, newBalance);
    }
}
