package com.lumier.service;

import java.util.ArrayList;
import java.util.List;
import com.lumier.domain.Expense;
import com.lumier.domain.Receiving;
import com.lumier.domain.SalePayment;
import com.lumier.domain.Transaction;
import com.lumier.repository.ExpenseRepository;
import com.lumier.repository.ReceivingRepository;
import com.lumier.repository.SalePaymentRepository;

import static com.lumier.domain.Transaction.TransactionType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private SalePaymentRepository salePaymentRepository;

    @Autowired
    private ReceivingRepository receivingRepository;

    public List<Transaction> getExpenses(String paymentType) {
        List<Expense> expenses = null;

        if (paymentType.equals(Transaction.ALL)) {
            expenses = expenseRepository.findAll();
        } else {
            expenses = expenseRepository.search(paymentType);
        }

        return expensesToTransactions(expenses);
    }

    public List<Transaction> getSalePayments(String paymentType) {
        List<SalePayment> salePayments = null;

        if (paymentType.equals(Transaction.ALL)) {
            salePayments = salePaymentRepository.findAll();
        } else {
            salePayments = salePaymentRepository.search(paymentType);
        }

        return salesPaymentToTransactions(salePayments);
    }

    public List<Transaction> getReceivings(String paymentType) {
        List<Receiving> receivings = null;

        if (paymentType.equals(Transaction.ALL)) {
            receivings = receivingRepository.findAll();
        } else {
            receivings = receivingRepository.search(paymentType);
        }

        return receivingsToTransactions(receivings);
    }

    // convert expenses to transactions
    private List<Transaction> expensesToTransactions(List<Expense> expenses) {
        List<Transaction> transactions = new ArrayList<Transaction>();
        Transaction transaction;
        for (Expense expense : expenses) {
            transaction = new Transaction();
            transaction.setId("E" + expense.getExpense_id());
            transaction.setAmount(expense.getAmount());
            transaction.setDate(expense.getDate());
            transaction.setTransactionType(TransactionType.Expense);
            transaction.setPaymentType(expense.getPayment_type());
            transaction.setDescription(expense.getDescription());
            transactions.add(transaction);
        }
        return transactions;
    }

    // convert sales payments to transactions
    private List<Transaction> salesPaymentToTransactions(List<SalePayment> salePayments) {
        List<Transaction> transactions = new ArrayList<Transaction>();
        Transaction transaction;
        for (SalePayment salePayment : salePayments) {
            transaction = new Transaction();
            transaction.setId("S" + salePayment.getPayment_id());
            transaction.setAmount(salePayment.getPayment_amount() - salePayment.getCash_refund());
            transaction.setDate(salePayment.getPayment_time());
            transaction.setTransactionType(TransactionType.Sale);
            transaction.setPaymentType(salePayment.getPayment_type());
            transaction.setDescription("Factura N° " + salePayment.getSale_id());
            transactions.add(transaction);
        }
        return transactions;
    }

    // convert expenses to transactions
    private List<Transaction> receivingsToTransactions(List<Receiving> receivings) {
        List<Transaction> transactions = new ArrayList<Transaction>();
        Transaction transaction;
        for (Receiving receiving : receivings) {
            transaction = new Transaction();
            transaction.setId("R" + receiving.getReceiving_id());
            transaction.setAmount(receiving.getAmount());
            transaction.setDate(receiving.getReceiving_time());
            transaction.setTransactionType(TransactionType.Receiving);
            transaction.setPaymentType(receiving.getPayment_type());
            transaction.setDescription("Compra N° " + receiving.getReceiving_id() + " - Proveedor N° "
                    + receiving.getSupplier_id() + " - " + receiving.getComment());
            transactions.add(transaction);
        }
        return transactions;
    }

}
