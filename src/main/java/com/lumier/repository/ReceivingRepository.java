package com.lumier.repository;

import java.sql.Date;
import java.util.List;

import com.lumier.domain.Receiving;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReceivingRepository extends JpaRepository<Receiving, Integer> {

    @Query(value = "SELECT receiving_id, receiving_time, payment_type, comment, supplier_id, amount FROM Ospos_receivings where payment_type = :paymentType and CAST(receiving_time AS DATE) between :fromDate AND :toDate", nativeQuery = true)
    List<Receiving> search(@Param("paymentType") String paymentType, @Param("fromDate") Date fromDate,
            @Param("toDate") Date toDate);

    @Query(value = "SELECT receiving_id, receiving_time, payment_type, comment, supplier_id, amount FROM Ospos_receivings where CAST(receiving_time AS DATE) between :fromDate AND :toDate", nativeQuery = true)
    List<Receiving> search(@Param("fromDate") Date fromDate, @Param("toDate") Date toDate);

    @Query(value = "SELECT receiving_id, receiving_time, payment_type, comment, supplier_id, amount FROM Ospos_receivings where amount is NULL", nativeQuery = true)
    List<Receiving> findWithNullAmount();

}
