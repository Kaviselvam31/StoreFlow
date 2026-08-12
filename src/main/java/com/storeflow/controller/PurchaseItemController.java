package com.storeflow.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.*;

import com.storeflow.entity.PurchaseItem;
import com.storeflow.service.PurchaseItemService;

@RestController
@RequestMapping("/purchase-item")
public class PurchaseItemController {

    private final PurchaseItemService purchaseItemService;

    public PurchaseItemController(PurchaseItemService purchaseItemService) {
        this.purchaseItemService = purchaseItemService;
    }

    // SAVE
    @PostMapping
    public PurchaseItem savePurchaseItem(
            @RequestBody PurchaseItem purchaseItem) {

        return purchaseItemService.savePurchaseItem(purchaseItem);
    }

    // GET ALL
    @GetMapping
    public List<PurchaseItem> getAllPurchaseItems() {
        return purchaseItemService.getAllPurchaseItems();
    }

    // GET BY ID
    @GetMapping("/{id}")
    public Optional<PurchaseItem> getPurchaseItemById(
            @PathVariable int id) {

        return purchaseItemService.getPurchaseItemById(id);
    }

    // UPDATE
    @PutMapping("/{id}")
    public PurchaseItem updatePurchaseItem(
            @PathVariable int id,
            @RequestBody PurchaseItem purchaseItem) {

        return purchaseItemService.updatePurchaseItem(id, purchaseItem);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public String deletePurchaseItem(@PathVariable int id) {

        return purchaseItemService.deletePurchaseItem(id);
    }
}