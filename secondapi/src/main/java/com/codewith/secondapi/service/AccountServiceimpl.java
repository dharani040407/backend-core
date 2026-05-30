package com.codewith.secondapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codewith.secondapi.Entities.Account;
import com.codewith.secondapi.Entities.Transaction;
import com.codewith.secondapi.repository.AccountRepository;
import com.codewith.secondapi.repository.TransactionRepository;

import java.util.List;

@Service
@Transactional
 @SuppressWarnings("null")
public class AccountServiceimpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    // ===================== ACCOUNT CREATION =====================

    @Override
    public Account createAccount(Account account) {
        // Check if account already exists with same name
        if (accountRepository.existsById(account.getAccountId())) {
            throw new RuntimeException("Account already exists with Id: " + account.getAccountId());
        }

        // Set initial balance to 0 if not provided
        if (account.getBalance() == null) {
            account.setBalance(0.0);
        }

        // Validate initial balance - cannot be negative
        if (account.getBalance() < 0) {
            throw new RuntimeException("Initial balance cannot be negative.");
        }

        Account savedAccount = accountRepository.save(account);

        // Log initial deposit transaction if balance > 0
        if (savedAccount.getBalance() > 0) {
            Transaction initialTransaction = new Transaction();
            initialTransaction.setAmount(savedAccount.getBalance());
            initialTransaction.setType("CREDIT");
            initialTransaction.setDetails("Initial deposit on account creation");
            initialTransaction.setAccount(savedAccount);
            transactionRepository.save(initialTransaction);
        }

        return savedAccount;
    }

    // ===================== DEPOSIT =====================

    @Override
   
    public Account deposit(Long accountId, Double amount) {
        // Validate deposit amount
        if (amount == null || amount <= 0) {
            throw new RuntimeException("Deposit amount must be greater than zero.");
        }

        // Fetch account
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found with ID: " + accountId));

        // Update balance
        double newBalance = account.getBalance() + amount;
        account.setBalance(newBalance);
        Account updatedAccount = accountRepository.save(account);

        // Log transaction
        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setType("CREDIT");
        transaction.setDetails("Deposit of " + amount + " to account: " + accountId);
        transaction.setAccount(updatedAccount);
        transactionRepository.save(transaction);

        return updatedAccount;
    }

    // ===================== WITHDRAW =====================

    @Override
    public Account withdraw(Long accountId, Double amount) {
        // Validate withdrawal amount
        if (amount == null || amount <= 0) {
            throw new RuntimeException("Withdrawal amount must be greater than zero.");
        }

        // Fetch account
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found with ID: " + accountId));

        // Check sufficient balance
        if (account.getBalance() < amount) {
            throw new RuntimeException("Insufficient balance. Available: "
                    + account.getBalance() + ", Requested: " + amount);
        }

        // Update balance
        double newBalance = account.getBalance() - amount;
        account.setBalance(newBalance);
        Account updatedAccount = accountRepository.save(account);

        // Log transaction
        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setType("DEBIT");
        transaction.setDetails("Withdrawal of " + amount + " from account: " + accountId);
        transaction.setAccount(updatedAccount);
        transactionRepository.save(transaction);

        return updatedAccount;
    }

    // ===================== GET ACCOUNT =====================

    @Override
    @Transactional(readOnly = true)
    public Account getAccountById(Long accountId) {
        return accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found with ID: " + accountId));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    // ===================== TRANSACTION HISTORY =====================

    @Override
    @Transactional(readOnly = true)
    public List<Transaction> getTransactionHistory(Long accountId) {
        // Verify account exists
        accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found with ID: " + accountId));

        return transactionRepository.findByAccountAccountId(accountId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Transaction> getTransactionsByType(Long accountId, String type) {
        // Validate type
        if (!type.equalsIgnoreCase("CREDIT") && !type.equalsIgnoreCase("DEBIT")) {
            throw new RuntimeException("Invalid transaction type. Use CREDIT or DEBIT.");
        }

        return transactionRepository.findByAccountAccountIdAndType(accountId, type.toUpperCase());
    }

    // ===================== BALANCE =====================

    @Override
    @Transactional(readOnly = true)
    public Double getTotalBalance(Long accountId) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found with ID: " + accountId));
        return account.getBalance();
    }

    // ===================== DELETE ACCOUNT =====================

    @Override
    public void deleteAccount(Long accountId) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found with ID: " + accountId));

        // Delete all transactions first
        transactionRepository.deleteByAccountId(accountId);

        // Delete account
        accountRepository.delete(account);
    }
}