package com.storeflow.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.storeflow.entity.Supplier;
import com.storeflow.repository.SupplierRepository;

@Service
public class SupplierService {

    private final SupplierRepository supplierRepository;

    public SupplierService(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    // SAVE
    public Supplier saveSupplier(Supplier supplier) {
        return supplierRepository.save(supplier);
    }

    // GET ALL
    public List<Supplier> getAllSuppliers() {
        return supplierRepository.findAll();
    }

    // GET BY ID
    public Optional<Supplier> getSupplierById(int id) {
        return supplierRepository.findById(id);
    }

    // UPDATE
    public Supplier updateSupplier(int id, Supplier supplier) {

        Supplier existingSupplier = supplierRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Supplier Not Found"));

        existingSupplier.setSupplierName(supplier.getSupplierName());
        existingSupplier.setContactPerson(supplier.getContactPerson());
        existingSupplier.setPhoneNumber(supplier.getPhoneNumber());
        existingSupplier.setEmail(supplier.getEmail());
        existingSupplier.setAddress(supplier.getAddress());
        existingSupplier.setGstNumber(supplier.getGstNumber());
        existingSupplier.setStatus(supplier.getStatus());

        return supplierRepository.save(existingSupplier);
    }

    // SOFT DELETE
    public String deleteSupplier(int id) {

        Supplier supplier = supplierRepository.findById(id)
                .orElse(null);

        if (supplier == null) {
            return "Supplier Not Found";
        }

        supplier.setStatus("Inactive");
        supplierRepository.save(supplier);

        return "Supplier Deactivated Successfully";
    }
}