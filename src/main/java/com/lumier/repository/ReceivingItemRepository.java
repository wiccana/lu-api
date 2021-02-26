package com.lumier.repository;

import java.util.List;
import com.lumier.domain.ReceivingItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReceivingItemRepository extends JpaRepository<ReceivingItem, Integer> {

    List<ReceivingItem> findByReceivingId(Integer receivingId);
}
