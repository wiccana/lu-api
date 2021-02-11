package com.lumier.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

import com.lumier.domain.Expense;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Integer> {

    @Query(value = "SELECT expense_id, date, payment_type, description, amount, expense_category_id FROM Ospos_expenses where payment_type = :paymentType", nativeQuery = true)
    List<Expense> search(@Param("paymentType") String paymentType);

}
