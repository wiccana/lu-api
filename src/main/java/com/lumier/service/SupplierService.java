package com.lumier.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.lumier.domain.Item;
import com.lumier.domain.ItemDetail;
import com.lumier.domain.ItemHistory;
import com.lumier.domain.Supplier;
import com.lumier.repository.ItemHistoryRepository;
import com.lumier.repository.ItemRepository;
import com.lumier.repository.SupplierRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;

    public List<Supplier> getAllSuppliers() {
        return supplierRepository.findAll();
    }

    public Supplier getSupplier(Integer id) {
        Optional<Supplier> optSupplier = supplierRepository.findById(id);
        if (optSupplier.isPresent())
            return optSupplier.get();

        return null;
    }

}
