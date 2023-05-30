package com.example.senebank.repositories;

import com.example.senebank.entities.AccountEntity;
import com.example.senebank.entities.TransactionEntity;
import com.example.senebank.entities.UserEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TransactionRepository extends JpaRepository<TransactionEntity, Integer> {

    Optional<AccountEntity> findByTransactionIdAndTransactionOwner(int transactionId, UserEntity transactionOwner);

    List<TransactionEntity> findAllByTransactionOwner(UserEntity transactionOwner);

    List<TransactionEntity> findAllByAccountFromId_AccountIdOrAccountToId_AccountId(int accountFromId, int accountToId);

    @Transactional
    @Modifying
    @Query("DELETE FROM TransactionEntity e " +
            "WHERE e.transactionId = :transactionId " +
            "AND e.transactionOwner = :transactionOwner")
    void deleteByTransactionIdAndTransactionOwner(int transactionId, UserEntity transactionOwner);
}
