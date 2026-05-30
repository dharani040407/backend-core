package com.codewith.secondapi.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import com.codewith.secondapi.Entities.*;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    // Find account by name
    Optional<Account> findByName(String name);

    // Find all accounts by address
    List<Account> findByAddress(String address);


    // Custom JPQL query - find account with all transactions
    @Query("SELECT a FROM Account a JOIN FETCH a.transactions WHERE a.accountId = :accountId")
    Optional<Account> findAccountWithTransactions(@Param("accountId") Long accountId);

    // Custom JPQL query - find accounts with balance above average
    @Query("SELECT a FROM Account a WHERE a.balance > (SELECT AVG(a2.balance) FROM Account a2)")
    List<Account> findAccountsAboveAverageBalance();

    // Native SQL query - find top accounts by balance
    @Query(value = "SELECT * FROM accounts ORDER BY balance DESC LIMIT :limit", nativeQuery = true)
    List<Account> findTopAccountsByBalance(@Param("limit") int limit);

    // Check if account exists by name
    boolean existsById(@NonNull Long Id);
}