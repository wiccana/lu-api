package com.lumier.domain;

import java.sql.Date;

public class Transaction {

    private String id;
    private String description;
    private Date date;
    private Double amount;
    private String paymentType;
    private TransactionType transactionType;

    public static final String CASH = "Efectivo";
    public static final String DEBIT = "Tarjeta de Débito";
    public static final String CREDIT = "Tarjeta de Crédito";
    public static final String ALL = "Todos";

    public static enum TransactionType {
        Expense, Receiving, Sale

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

}
