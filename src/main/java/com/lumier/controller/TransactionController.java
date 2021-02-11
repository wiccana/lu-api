package com.lumier.controller;

import java.sql.Date;
import java.util.List;

import com.lumier.domain.Transaction;
import com.lumier.service.TransactionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @GetMapping(path = "/transaction")
    public ResponseEntity<List<Transaction>> findAllTransactions(@RequestParam(required = false) Date date,
            @RequestParam(required = false) String paymentType) {

        switch (paymentType) {
            case "cash":
                paymentType = Transaction.CASH;
                break;
            case "debit":
                paymentType = Transaction.DEBIT;
                break;
            case "credit":
                paymentType = Transaction.CREDIT;
                break;
            default:
                paymentType = Transaction.ALL;

        }

        List<Transaction> transactions = transactionService.getExpenses(paymentType);
        transactions.addAll(transactionService.getSalePayments(paymentType));
        transactions.addAll(transactionService.getReceivings(paymentType));

        return new ResponseEntity<>(transactions, HttpStatus.OK);

    }

}
