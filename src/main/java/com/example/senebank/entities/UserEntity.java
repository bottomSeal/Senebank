package com.example.senebank.entities;

import com.example.senebank.models.enums.UserRole;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class UserEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID userId;

    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    private LocalDate registerDate;

    @OneToMany
    @JoinColumn(name = "account_owner_user_id")
    private List<AccountEntity> ownedAccounts;

    @OneToMany
    @JoinColumn(name = "transaction_owner_user_id")
    private List<TransactionEntity> ownedTransactions;
}
