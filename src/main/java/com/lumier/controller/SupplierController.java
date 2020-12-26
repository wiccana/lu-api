package com.lumier.controller;

import java.util.List;

import com.lumier.domain.Supplier;
import com.lumier.service.SupplierService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SupplierController {

    @Autowired
    SupplierService supplierService;

    @GetMapping(path = "/supplier")
    public ResponseEntity<List<Supplier>> findAllSuppliers() {

        try {

            List<Supplier> suppliers = supplierService.getAllSuppliers();

            return new ResponseEntity<>(suppliers, HttpStatus.OK);

        } catch (Exception e) {

            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
