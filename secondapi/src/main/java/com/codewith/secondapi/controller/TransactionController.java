package com.codewith.secondapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.codewith.Entities.Transaction;
import com.codewith.secondapi.service.AccountService;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    private AccountService accountService;

    // ===================== GET ALL TRANSACTIONS =====================
    @GetMapping("/{accountId}/history")
    public ResponseEntity<List<Transaction>> getTransactionHistory(
            @PathVariable Long accountId) {
        List<Transaction> transactions = accountService.getTransactionHistory(accountId);
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }

    // ===================== GET TRANSACTIONS BY TYPE =====================
    @GetMapping("/{accountId}/type")
    public ResponseEntity<List<Transaction>> getTransactionsByType(
            @PathVariable Long accountId,
            @RequestParam String type) {
        List<Transaction> transactions = accountService.getTransactionsByType(accountId, type);
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }
}