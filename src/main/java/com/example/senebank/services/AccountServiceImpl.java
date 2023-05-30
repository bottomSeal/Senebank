package com.example.senebank.services;

import com.example.senebank.dao.AccountDao;
import com.example.senebank.dao.UserDao;
import com.example.senebank.entities.AccountEntity;
import com.example.senebank.jwt.JwtService;
import com.example.senebank.jwt.JwtUtils;
import com.example.senebank.messages.requests.AccountClosureRequest;
import com.example.senebank.messages.requests.AccountInfoRequest;
import com.example.senebank.messages.requests.AccountRegisterRequest;
import com.example.senebank.models.AccountModel;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
@Slf4j
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService{

    private final AccountDao accountDao;

    private final UserDao userDao;

    private final JwtService jwtService;

    private final HttpServletRequest request;

    @Override
    public AccountModel register(AccountRegisterRequest accountRegisterRequest) {

        AccountEntity newAccount = accountDao.createAccount(
                accountRegisterRequest,
                userDao.getUserByEmail(
                        jwtService.parseToken(JwtUtils.getTokenFromRequest(request))
                                .getEmail()
                )
        );

        return AccountModel.fromEntity(newAccount);
    }

    @Override
    public AccountModel closure(AccountClosureRequest accountClosureRequest) {

        accountDao.deleteByIdAndOwner(accountClosureRequest.getAccountId(),
                userDao.getUserByEmail(
                        jwtService.parseToken(JwtUtils.getTokenFromRequest(request))
                                .getEmail()
                )
        );

        return AccountModel.builder().accountId(accountClosureRequest.getAccountId()).build();
    }

    @Override
    public AccountModel info(AccountInfoRequest accountInfoRequest) {
        return AccountModel.fromEntity(accountDao.getAccountByIdAndOwner(accountInfoRequest.getAccountId(),
                        userDao.getUserByEmail(
                                jwtService.parseToken(JwtUtils.getTokenFromRequest(request))
                                        .getEmail()
                        )
                )
        );

    }


}
