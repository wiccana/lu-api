package com.lumier.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.lumier.domain.Expense;
import com.lumier.domain.Receiving;
import com.lumier.domain.ReceivingItem;
import com.lumier.domain.ReceivingItemId;
import com.lumier.domain.SalePayment;
import com.lumier.domain.Transaction;
import com.lumier.repository.ExpenseRepository;
import com.lumier.repository.ReceivingItemRepository;
import com.lumier.repository.ReceivingRepository;
import com.lumier.repository.SalePaymentRepository;

import static com.lumier.domain.Transaction.TransactionType;

import org.hibernate.Criteria;
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

    @Autowired
    private ReceivingItemRepository receivingItemRepository;

    public List<Transaction> getExpenses(String paymentType, Date fromDate, Date toDate) {
        List<Expense> expenses = null;

        if (paymentType.equals(Transaction.ALL)) {
            expenses = expenseRepository.findAll();
        } else {
            expenses = expenseRepository.search(paymentType, fromDate, toDate);
        }

        return expensesToTransactions(expenses);
    }

    public List<Transaction> getSalePayments(String paymentType, Date fromDate, Date toDate) {
        List<SalePayment> salePayments = null;

        if (paymentType.equals(Transaction.ALL)) {
            salePayments = salePaymentRepository.findAll();
        } else {
            salePayments = salePaymentRepository.search(paymentType, fromDate, toDate);
        }

        return salesPaymentToTransactions(salePayments);
    }

    public List<Transaction> getReceivings(String paymentType, Date fromDate, Date toDate) {
        List<Receiving> receivings = null;

        if (paymentType.equals(Transaction.ALL)) {
            receivings = receivingRepository.findAll();
        } else {
            receivings = receivingRepository.search(paymentType, fromDate, toDate);
        }

        return receivingsToTransactions(receivings);
    }

    public void updateReceivingsAmount() {
        List<Receiving> receivings = receivingRepository.findWithNullAmount();
        List<ReceivingItem> receivingItems;
        Double amount;
        for (Receiving receiving : receivings) {
            /*
             * Criteria criteria = session.createCriteria(YourClass.class); YourObject
             * yourObject = criteria.add(Restrictions.eq("yourField", yourFieldValue))
             * .uniqueResult();
             */
            receivingItems = receivingItemRepository.findByReceivingId(receiving.getReceiving_id());
            amount = 0d;
            for (ReceivingItem receivingItem : receivingItems) {
                amount += receivingItem.getItemCostPrice() * receivingItem.getQuantityPurchased();
            }
            receiving.setAmount(Double.valueOf(amount));
            receivingRepository.save(receiving);
        }

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
