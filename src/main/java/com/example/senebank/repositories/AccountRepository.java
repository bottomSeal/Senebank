package com.example.senebank.repositories;

import com.example.senebank.entities.AccountEntity;
import com.example.senebank.entities.UserEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<AccountEntity, Integer> {

    Optional<AccountEntity> findByAccountIdAndAccountOwner(int accountId, UserEntity accountOwner);

    List<AccountEntity> findAllByAccountOwner(UserEntity accountOwner);

    @Transactional
    @Modifying
    @Query("DELETE FROM AccountEntity e " +
            "WHERE e.accountId = :accountId " +
            "AND e.accountOwner = :accountOwner")
    void deleteByAccountIdAndAccountOwner(int accountId, UserEntity accountOwner);

    @Transactional
    @Modifying
    @Query("UPDATE AccountEntity e " +
            "SET e.balance = :newBalance " +
            "WHERE e.accountId = :accountId")
    void setBalance(int accountId, int newBalance);
}
