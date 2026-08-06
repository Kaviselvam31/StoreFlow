package com.storeflow.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.*;

import com.storeflow.entity.Supplier;
import com.storeflow.service.SupplierService;

@RestController
@RequestMapping("/supplier")
public class SupplierController {

    private final SupplierService supplierService;

    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    // SAVE
    @PostMapping
    public Supplier saveSupplier(@RequestBody Supplier supplier) {
        return supplierService.saveSupplier(supplier);
    }

    // GET ALL
    @GetMapping
    public List<Supplier> getAllSuppliers() {
        return supplierService.getAllSuppliers();
    }

    // GET BY ID
    @GetMapping("/{id}")
    public Optional<Supplier> getSupplierById(@PathVariable int id) {
        return supplierService.getSupplierById(id);
    }

    // UPDATE
    @PutMapping("/{id}")
    public Supplier updateSupplier(@PathVariable int id,
                                   @RequestBody Supplier supplier) {
        return supplierService.updateSupplier(id, supplier);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public String deleteSupplier(@PathVariable int id) {
        return supplierService.deleteSupplier(id);
    }
}