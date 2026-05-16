package com.codewith.secondapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.codewith.Entities.Account;
import com.codewith.secondapi.service.AccountService;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    // ===================== CREATE ACCOUNT =====================
    @PostMapping("/create")
    public ResponseEntity<Account> createAccount(@RequestBody Account account) {
        Account created = accountService.createAccount(account);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    // ===================== DEPOSIT =====================
    @PutMapping("/{accountId}/deposit")
    public ResponseEntity<Account> deposit(
            @PathVariable Long accountId,
            @RequestParam Double amount) {
        Account updated = accountService.deposit(accountId, amount);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    // ===================== WITHDRAW =====================
    @PutMapping("/{accountId}/withdraw")
    public ResponseEntity<Account> withdraw(
            @PathVariable Long accountId,
            @RequestParam Double amount) {
        Account updated = accountService.withdraw(accountId, amount);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    // ===================== GET ACCOUNT BY ID =====================
    @GetMapping("/{accountId}")
    public ResponseEntity<Account> getAccountById(@PathVariable Long accountId) {
        Account account = accountService.getAccountById(accountId);
        return new ResponseEntity<>(account, HttpStatus.OK);
    }

    // ===================== GET ALL ACCOUNTS =====================
    @GetMapping("/all")
    public ResponseEntity<List<Account>> getAllAccounts() {
        List<Account> accounts = accountService.getAllAccounts();
        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }

    // ===================== GET BALANCE =====================
    @GetMapping("/{accountId}/balance")
    public ResponseEntity<Double> getBalance(@PathVariable Long accountId) {
        Double balance = accountService.getTotalBalance(accountId);
        return new ResponseEntity<>(balance, HttpStatus.OK);
    }

    // ===================== DELETE ACCOUNT =====================
    @DeleteMapping("/{accountId}/delete")
    public ResponseEntity<String> deleteAccount(@PathVariable Long accountId) {
        accountService.deleteAccount(accountId);
        return new ResponseEntity<>("Account deleted successfully.", HttpStatus.OK);
    }
}