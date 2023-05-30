package com.example.senebank.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "accounts")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class AccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int accountId;

    private int balance;

    private boolean isOverdraft;

    @ManyToOne(targetEntity = UserEntity.class)
    private UserEntity accountOwner;

    @OneToMany
    @JoinColumn(name = "account_to_id_account_id")
    private List<TransactionEntity> receivedTransaction;

    @OneToMany
    @JoinColumn(name = "account_from_id_account_id")
    private List<TransactionEntity> transferredTransaction;
}
