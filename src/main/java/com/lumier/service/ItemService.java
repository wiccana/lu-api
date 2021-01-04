package com.lumier.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import com.lumier.domain.Item;
import com.lumier.domain.ItemDetail;
import com.lumier.domain.ItemHistory;
import com.lumier.repository.ItemHistoryRepository;
import com.lumier.repository.ItemRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private SupplierService supplierService;

    @Autowired
    private ItemHistoryRepository itemHistoryRepository;

    public List<ItemDetail> getItems(Integer supplierId, Boolean excludeToday) {
        List<Item> items = null;
        if (supplierId == null) {
            items = itemRepository.findAll();
        } else {
            items = itemRepository.findBySupplierId(supplierId);
        }
        return getDetailsList(items, excludeToday);
    }

    // NO USAR
    public List<ItemDetail> getItemsByCategory(String category) {
        List<Item> items = itemRepository.findByCategoryContaining(category);
        List<ItemDetail> detailsList = new ArrayList<ItemDetail>();
        for (Item item : items) {
            ItemDetail detail = new ItemDetail();
            detail.setItem_id(item.getItem_id());
            detail.setCategory(item.getCategory());
            detail.setUnitCost(item.getCost_price());
            detail.setUnitPrice(item.getUnit_price());

            // agrego a la lista
            detailsList.add(detail);
        }

        return detailsList;
    }

    public List<Item> createHistoryForNewItems() {
        List<Item> itemsWithoutHistory = itemRepository.findItemsWithoutHistory();
        if (itemsWithoutHistory == null) {
            return null;
        }

        for (Item item : itemsWithoutHistory) {

            ItemHistory newItemHistory = new ItemHistory();
            newItemHistory.setItem_id(item.getItem_id());
            newItemHistory.setCost_price(item.getCost_price());
            newItemHistory.setUnit_price(item.getUnit_price());
            newItemHistory.setDate(new Date());
            // saves fresh created history
            itemHistoryRepository.saveAndFlush(newItemHistory);

            // update Item History to TRUE
            item.setHistory(true);
            itemRepository.save(item);
        }

        return itemsWithoutHistory;

    }

    public void updateItemsHistory(List<ItemHistory> itemHistoryList) {
        Optional<Item> optionalItem;
        Item item;

        for (ItemHistory itemHistory : itemHistoryList) {

            // saves items
            optionalItem = itemRepository.findById(itemHistory.getItem_id());
            if (optionalItem.isPresent()) {
                item = optionalItem.get();

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

    private List<ItemDetail> getDetailsList(List<Item> items, Boolean excludeToday) {
        // get suppliers names
        HashMap<Integer, String> suppliers = supplierService.getSuppliersMap();
        // formatted date
        SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm dd-MMM");
        String todayFormatted = simpleFormat.format(new Date()).toString();
        String itemDateFormatted = "";
        Date itemDate = null;

        List<ItemDetail> detailsList = new ArrayList<ItemDetail>();
        for (Item item : items) {
            itemDate = getLastHistoryDate(item);

            if (excludeToday) {
                itemDateFormatted = simpleFormat.format(itemDate).toString();
                if (todayFormatted.equals(itemDateFormatted)) {
                    continue;
                }
            }

            ItemDetail detail = new ItemDetail();
            detail.setSupplierName(suppliers.get(item.getSupplier_id()));
            detail.setName(item.getName());
            detail.setItem_id(item.getItem_id());
            detail.setItemNumber(item.getItem_number());
            detail.setCategory(item.getCategory());
            // get las history date
            detail.setUpdate(dateFormat.format(itemDate).toString());
            detail.setUnitCost(item.getCost_price());
            detail.setUnitPrice(item.getUnit_price());

            // agrego a la lista
            detailsList.add(detail);
        }

        return detailsList;

    }

    private Date getLastHistoryDate(Item item) {
        ItemHistory itemHistory = itemHistoryRepository.getLastItemHistory(item.getItem_id());
        if (itemHistory == null) {
            return null;
        }
        return itemHistory.getDate();
    }

}
