package com.lumier.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.lumier.domain.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {

    @Query(value = "SELECT item_id, item_number, name, category, supplier_id, description, cost_price, unit_price FROM Ospos_items OT where OT.supplier_id = :supplierId", nativeQuery = true)
    List<Item> findBySupplierId(@Param("supplierId") Integer supplierId);

    @Query(value = "SELECT item_id, item_number, name, category, supplier_id, description, cost_price, unit_price FROM Ospos_items OT where OT.category = :category", nativeQuery = true)
    List<Item> findByCategory(@Param("category") String category);
}
