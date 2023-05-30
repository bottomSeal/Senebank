package com.example.senebank.models;

import com.example.senebank.entities.AccountEntity;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class AccountModel {

    private final int accountId;

    private int balance;

    private boolean isOverdraft;

    private String emailOwner;

    public static AccountModel fromEntity(AccountEntity accountEntity) {
        return AccountModel.builder()
                .accountId(accountEntity.getAccountId())
                .balance(accountEntity.getBalance())
                .isOverdraft(accountEntity.isOverdraft())
                .emailOwner(accountEntity.getAccountOwner().getEmail())
                .build();
    }
}
