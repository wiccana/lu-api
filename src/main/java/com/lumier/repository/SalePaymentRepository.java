package com.lumier.repository;

import java.sql.Date;
import java.util.List;

import com.lumier.domain.SalePayment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SalePaymentRepository extends JpaRepository<SalePayment, Integer> {

    @Query(value = "SELECT payment_id, payment_time, payment_type, sale_id, payment_amount, cash_refund FROM ospos_sales_payments where payment_type = :paymentType and CAST(payment_time AS DATE) between :fromDate AND :toDate and sale_id in (select sale_id from ospos_sales where sale_status = 0)", nativeQuery = true)
    List<SalePayment> search(@Param("paymentType") String paymentType, @Param("fromDate") Date fromDate,
            @Param("toDate") Date toDate);

}
