package com.lumier.domain;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ospos_receivings")
public class Receiving {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer receiving_id;

    private Date receiving_time;
    private Integer supplier_id;
    private String payment_type;
    private String comment;

    // columna añadida y calculada
    private Double amount;

    public Integer getReceiving_id() {
        return receiving_id;
    }

    public void setReceiving_id(Integer receiving_id) {
        this.receiving_id = receiving_id;
    }

    public Date getReceiving_time() {
        return receiving_time;
    }

    public void setReceiving_time(Date receiving_time) {
        this.receiving_time = receiving_time;
    }

    public Integer getSupplier_id() {
        return supplier_id;
    }

    public void setSupplier_id(Integer supplier_id) {
        this.supplier_id = supplier_id;
    }

    public String getPayment_type() {
        return payment_type;
    }

    public void setPayment_type(String payment_type) {
        this.payment_type = payment_type;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

}
