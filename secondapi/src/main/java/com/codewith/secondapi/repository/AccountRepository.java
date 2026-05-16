package com.codewith.secondapi.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import com.codewith.Entities.*;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    // Find account by name
    Optional<Account> findByName(String name);

    // Find all accounts by address
    List<Account> findByAddress(String address);

    // Find account with balance greater than given amount
    List<Account> findByBalanceGreaterThan(Double balance);

    // Find account with balance less than given amount
    List<Account> findByBalanceLessThan(Double balance);

    // Find account with balance between two values
    List<Account> findByBalanceBetween(Double minBalance, Double maxBalance);

    // Find account by name containing keyword (like search)
    List<Account> findByNameContaining(String keyword);

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
    boolean existsByName(String name);
}