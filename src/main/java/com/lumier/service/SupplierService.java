package com.lumier.service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import com.lumier.domain.Supplier;
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

    public HashMap<Integer, String> getSuppliersMap() {
        List<Supplier> suppliersList = supplierRepository.findAll();
        HashMap<Integer, String> suppliersMap = new HashMap<Integer, String>();
        for (Supplier supplier : suppliersList) {
            suppliersMap.put(supplier.getPerson_id(), supplier.getCompany_name());
        }
        return suppliersMap;

    }

    public Supplier getSupplier(Integer id) {
        Optional<Supplier> optSupplier = supplierRepository.findById(id);
        if (optSupplier.isPresent())
            return optSupplier.get();

        return null;
    }

}
