package com.codewith.secondapi.service;
import java.util.List;

import com.codewith.secondapi.Entities.*;


public interface AccountService {
    Account createAccount(Account account);
    Account deposit(Long accountId, Double amount);
    Account withdraw(Long accountId, Double amount);
    Account getAccountById(Long accountId);
    List<Account> getAllAccounts();
    List<Transaction> getTransactionHistory(Long accountId);
    List<Transaction> getTransactionsByType(Long accountId, String type);
    Double getTotalBalance(Long accountId);
    void deleteAccount(Long accountId);
    //Authen findEmail(String email);
}