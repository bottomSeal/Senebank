package com.example.senebank.services;

import com.example.senebank.dao.AccountDao;
import com.example.senebank.dao.UserDao;
import com.example.senebank.entities.AccountEntity;
import com.example.senebank.messages.requests.AccountClosureRequest;
import com.example.senebank.messages.requests.AccountInfoRequest;
import com.example.senebank.messages.requests.AccountRegisterRequest;
import com.example.senebank.models.AccountModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService{

    private final AccountDao accountDao;

    private final UserDao userDao;

    @Override
    public AccountModel register(AccountRegisterRequest accountRegisterRequest) {

        AccountEntity newAccount = accountDao.createAccount(
                accountRegisterRequest,
                userDao.getUserByEmail(
                        (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal()
                )
        );

        return AccountModel.fromEntity(newAccount);
    }

    @Override
    public AccountModel closure(AccountClosureRequest accountClosureRequest) {

        accountDao.deleteByIdAndOwner(accountClosureRequest.getAccountId(),
                userDao.getUserByEmail(
                        (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal()
                )
        );

        return AccountModel.builder().accountId(accountClosureRequest.getAccountId()).build();
    }

    @Override
    public AccountModel info(AccountInfoRequest accountInfoRequest) {
        return AccountModel.fromEntity(accountDao.getAccountByIdAndOwner(accountInfoRequest.getAccountId(),
                        userDao.getUserByEmail(
                                (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal()
                        )
                )
        );

    }

}
