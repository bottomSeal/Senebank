package com.example.senebank.models;

import com.example.senebank.entities.TransactionEntity;
import lombok.*;


@Getter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class TransactionModel {

    private int payload;

    private int accountFromId;

    private int accountToId;

    private String transactionOwnerEmail;

    public static TransactionModel fromEntity(TransactionEntity transactionEntity) {
        return TransactionModel.builder()
                .payload(transactionEntity.getPayload())
                .accountFromId(transactionEntity.getAccountFromId().getAccountId())
                .accountToId(transactionEntity.getAccountToId().getAccountId())
                .transactionOwnerEmail(transactionEntity.getTransactionOwner().getEmail())
                .build();
    }
}
