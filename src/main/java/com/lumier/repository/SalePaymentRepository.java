package com.lumier.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.lumier.domain.SalePayment;

@Repository
public interface SalePaymentRepository extends JpaRepository<SalePayment, Integer> {

    @Query(value = "SELECT payment_id, payment_time, payment_type, sale_id, payment_amount, cash_refund FROM ospos_sales_payments where payment_type = :paymentType", nativeQuery = true)
    List<SalePayment> search(@Param("paymentType") String paymentType);

}
