package com.storeflow.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.*;

import com.storeflow.entity.Purchase;
import com.storeflow.service.PurchaseService;

@RestController
@RequestMapping("/purchase")
public class PurchaseController {

    private final PurchaseService purchaseService;

    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    // SAVE
    @PostMapping
    public Purchase savePurchase(@RequestBody Purchase purchase) {
        return purchaseService.savePurchase(purchase);
    }

    // GET ALL
    @GetMapping
    public List<Purchase> getAllPurchases() {
        return purchaseService.getAllPurchases();
    }

    // GET BY ID
    @GetMapping("/{id}")
    public Optional<Purchase> getPurchaseById(@PathVariable int id) {
        return purchaseService.getPurchaseById(id);
    }

    // UPDATE
    @PutMapping("/{id}")
    public Purchase updatePurchase(
            @PathVariable int id,
            @RequestBody Purchase purchase) {

        return purchaseService.updatePurchase(id, purchase);
    }
    
    // DELETE
    @DeleteMapping("/{id}")
    public String deletePurchase(@PathVariable int id) {
        return purchaseService.deletePurchase(id);
    }
}