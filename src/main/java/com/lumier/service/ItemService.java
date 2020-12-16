package com.lumier.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.lumier.domain.Item;
import com.lumier.domain.ItemDetail;
import com.lumier.domain.ItemHistory;
import com.lumier.repository.ItemHistoryRepository;
import com.lumier.repository.ItemRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ItemHistoryRepository itemHistoryRepository;

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

    public void updateItemsHistory(List<ItemHistory> itemHistoryList) {
        Optional<Item> optionalItem;
        Item item;

        for (ItemHistory itemHistory : itemHistoryList) {

            // saves items
            optionalItem = itemRepository.findById(itemHistory.getItem_id());
            if (optionalItem.isPresent()) {
                item = optionalItem.get();

                // save new items automatic history before update
                if (item.getHistory()) {
                    ItemHistory previousItemHistory = new ItemHistory();
                    previousItemHistory.setCost_price(item.getCost_price());
                    previousItemHistory.setUnit_price(item.getUnit_price());
                    previousItemHistory.setDate(new Date());
                    itemHistoryRepository.saveAndFlush(previousItemHistory);

                }

                // updates item with new values
                item.setCost_price(itemHistory.getCost_price());
                item.setUnit_price(itemHistory.getUnit_price());
                item.setHistory(true);
                itemRepository.save(item);
            }

            // saves itemHistory
            itemHistory.setDate(new Date());
            itemHistoryRepository.saveAndFlush(itemHistory);

        }

    }

}
