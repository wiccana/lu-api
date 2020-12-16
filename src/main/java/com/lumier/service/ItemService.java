package com.lumier.service;

import java.util.ArrayList;
import java.util.List;

import com.lumier.domain.Item;
import com.lumier.domain.ItemDetail;
import com.lumier.repository.ItemHistoryRepository;
import com.lumier.repository.ItemRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    // @Autowired
    // private ItemHistoryRepository itemHistoryRepository;

    public List<ItemDetail> getItemsBySupplier(Integer supplierId) {
        List<Item> items = itemRepository.findBySupplierId(supplierId);
        List<ItemDetail> detailsList = new ArrayList<ItemDetail>();
        ItemDetail detail = new ItemDetail();
        for (Item item : items) {

            detail.setItem_id(item.getItem_id());
            detail.setCategory(item.getCategory());
            detail.setUnitCost(item.getCost_price());
            detail.setUnitPrice(item.getUnit_price());

            // agrego a la lista
            detailsList.add(detail);
        }

        return detailsList;
    }

    public List<ItemDetail> getItemsByCategory(String category) {
        List<Item> items = itemRepository.findByCategory(category);
        List<ItemDetail> detailsList = new ArrayList<ItemDetail>();
        ItemDetail detail = new ItemDetail();
        for (Item item : items) {

            detail.setItem_id(item.getItem_id());
            detail.setCategory(item.getCategory());
            detail.setUnitCost(item.getCost_price());
            detail.setUnitPrice(item.getUnit_price());

            // agrego a la lista
            detailsList.add(detail);
        }

        return detailsList;
    }

}
