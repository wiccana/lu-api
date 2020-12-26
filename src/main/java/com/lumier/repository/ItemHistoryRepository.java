package com.lumier.repository;

import com.lumier.domain.ItemHistory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ItemHistoryRepository extends JpaRepository<ItemHistory, Integer> {

    // @Query(value = "SELECT item_id, item_number, name, category, supplier_id,
    // description, cost_price, unit_price, history FROM Ospos_items OT where
    // OT.supplier_id = :supplierId", nativeQuery = true)
    // List<Item> findBySupplierId(@Param("supplierId") Integer supplierId);

    @Query(value = "SELECT id, MAX(date) date, item_id, unit_price, cost_price FROM Lu_items_history where item_id = :itemId", nativeQuery = true)
    ItemHistory getLastItemHistory(@Param("itemId") Integer itemId);

}
