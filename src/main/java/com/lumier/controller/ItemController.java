package com.lumier.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import com.lumier.domain.ItemDetail;
import com.lumier.domain.ItemHistory;
import com.lumier.service.ItemService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping(path = "/test")
    public ResponseEntity<String> test() {
        return new ResponseEntity<>("hola", HttpStatus.OK);
    }

    @GetMapping(path = "/details")
    public ResponseEntity<List<ItemDetail>> getItemsBySupplier(@RequestParam Integer supplier,
            @RequestParam String category) {

        try {

            List<ItemDetail> items = null;

            if (supplier != null) {
                items = itemService.getItemsBySupplier(supplier);
            } else if (category != null) {
                items = itemService.getItemsByCategory(category);
            }

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

}
