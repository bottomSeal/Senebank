package com.example.senebank.entities;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "transactions")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class TransactionEntity  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int transactionId;

    private int payload;

    @ManyToOne(targetEntity = AccountEntity.class)
    private AccountEntity accountFromId;

    @ManyToOne(targetEntity = UserEntity.class)
    private UserEntity transactionOwner;

    @ManyToOne(targetEntity = AccountEntity.class)
    private AccountEntity accountToId;
}
