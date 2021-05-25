package com.lumier.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

import com.lumier.domain.Expense;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Integer> {

    @Query(value = "SELECT expense_id, date, payment_type, description, amount, expense_category_id FROM Ospos_expenses where deleted = 0 and payment_type = :paymentType and CAST(date AS DATE) between :fromDate AND :toDate", nativeQuery = true)
    List<Expense> search(@Param("paymentType") String paymentType, @Param("fromDate") Date fromDate,
            @Param("toDate") Date toDate);

    @Query(value = "SELECT expense_id, date, payment_type, description, amount, expense_category_id FROM Ospos_expenses where deleted = 0 and CAST(date AS DATE) between :fromDate AND :toDate", nativeQuery = true)
    List<Expense> search(@Param("fromDate") Date fromDate, @Param("toDate") Date toDate);

}
