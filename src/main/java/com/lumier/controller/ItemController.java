package com.lumier.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// import com.lumier.domain.Item;
import com.lumier.domain.ItemDetail;
// import com.lumier.domain.ItemHistory;
// import com.lumier.repository.ItemHistoryRepository;
// import com.lumier.repository.ItemRepository;
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
        // Document document = MongoReader.getDocument(POPTIONS,"pcategories");
        System.out.println("testing");
        return new ResponseEntity<>("hola", HttpStatus.OK);
    }

    @GetMapping(path = "/details")
    public ResponseEntity<List<ItemDetail>> getItemsBySupplier(@RequestParam Integer supplier) {
        try {
            List<ItemDetail> items = itemService.getItemsBySupplier(supplier);
            return new ResponseEntity<>(items, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/details2")
    public ResponseEntity<List<ItemDetail>> getItemsByCategory(@RequestParam String category) {
        try {
            List<ItemDetail> items = itemService.getItemsByCategory(category);
            return new ResponseEntity<>(items, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
