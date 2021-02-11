package com.lumier.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.lumier.domain.Receiving;

@Repository
public interface ReceivingRepository extends JpaRepository<Receiving, Integer> {

    @Query(value = "SELECT receiving_id, receiving_time, payment_type, comment, supplier_id, amount FROM Ospos_receivings where payment_type = :paymentType", nativeQuery = true)
    List<Receiving> search(@Param("paymentType") String paymentType);

}
