package com.lumier.controller;

import java.util.List;

import com.lumier.domain.Item;
import com.lumier.domain.ItemDetail;
import com.lumier.domain.ItemHistory;
import com.lumier.service.ItemService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping(path = "/test")
    public ResponseEntity<String> test() {
        return new ResponseEntity<>("hola", HttpStatus.OK);
    }

    @GetMapping(path = "/details")
    public ResponseEntity<List<ItemDetail>> getItems(@RequestParam Integer supplier, @RequestParam String category,
            @RequestParam Boolean extoday) {
        if (extoday == null)
            extoday = false;

        try {
            List<ItemDetail> items = null;

            if (supplier != null) {
                items = itemService.getItems(supplier, extoday);
            }
            // else if (category != null) {
            // items = itemService.getItemsByCategory(category);
            // }

            return new ResponseEntity<>(items, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(path = "/items")
    public ResponseEntity<List<ItemHistory>> updateItemsHistory(@RequestBody List<ItemHistory> itemHistoryList) {

        try {

            itemService.updateItemsHistory(itemHistoryList);

            return new ResponseEntity<>(itemHistoryList, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(path = "/items/createHistory")
    public ResponseEntity<List<Item>> createHistoryForNewItems(@RequestBody List<ItemHistory> itemHistoryList) {

        try {

            List<Item> updatedItems = itemService.createHistoryForNewItems();

            return new ResponseEntity<>(updatedItems, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
