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
    public ResponseEntity<List<Transaction>> findAllTransactions(@RequestParam(required = false) Date fromDate,
            @RequestParam(required = false) Date toDate, @RequestParam(required = false) String paymentType) {

        if (paymentType == null) {
            paymentType = "all";
        }
        if (fromDate == null || toDate == null) {
            fromDate = new Date(System.currentTimeMillis());
            toDate = fromDate;
        }

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

        List<Transaction> transactions = transactionService.getExpenses(paymentType, fromDate, toDate);
        transactions.addAll(transactionService.getSalePayments(paymentType, fromDate, toDate));
        transactions.addAll(transactionService.getReceivings(paymentType, fromDate, toDate));

        return new ResponseEntity<>(transactions, HttpStatus.OK);

    }

}
