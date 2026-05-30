package com.codewith.secondapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.codewith.secondapi.Entities.*;

import java.util.List;
@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    // Find all transactions by account ID
    List<Transaction> findByAccountAccountId(Long accountId);

    // Find transactions by type (CREDIT / DEBIT)
    List<Transaction> findByType(String type);

    // Find transactions by account ID and type
    List<Transaction> findByAccountAccountIdAndType(Long accountId, String type);

    // Find transactions with amount greater than given value
    List<Transaction> findByAmountGreaterThan(Double amount);

    // Find transactions with amount less than given value
    List<Transaction> findByAmountLessThan(Double amount);

    // Find transactions by details containing keyword
    List<Transaction> findByDetailsContaining(String keyword);

    // Custom JPQL - total amount spent by an account
    @Query("SELECT SUM(t.amount) FROM Transaction t WHERE t.account.accountId = :accountId")
    Double findTotalAmountByAccountId(@Param("accountId") Long accountId);

    // Custom JPQL - find all CREDIT transactions for an account
    @Query("SELECT t FROM Transaction t WHERE t.account.accountId = :accountId AND t.type = 'CREDIT'")
    List<Transaction> findCreditTransactions(@Param("accountId") Long accountId);

    // Custom JPQL - find all DEBIT transactions for an account
    @Query("SELECT t FROM Transaction t WHERE t.account.accountId = :accountId AND t.type = 'DEBIT'")
    List<Transaction> findDebitTransactions(@Param("accountId") Long accountId);

    // Native SQL - find top transactions by amount for an account
    @Query(value = "SELECT * FROM transactions WHERE account_id = :accountId ORDER BY amount DESC LIMIT :limit", nativeQuery = true)
    List<Transaction> findTopTransactionsByAmount(@Param("accountId") Long accountId, @Param("limit") int limit);

    // Modifying query - delete all transactions by account ID
    @Modifying
    @Transactional
    @Query("DELETE FROM Transaction t WHERE t.account.accountId = :accountId")
    void deleteByAccountId(@Param("accountId") Long accountId);

    // Count transactions by account
    long countByAccountAccountId(Long accountId);
}