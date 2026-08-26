package com.storeflow.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.storeflow.entity.PurchaseItem;
import com.storeflow.entity.Purchase;
import com.storeflow.repository.PurchaseItemRepository;
import com.storeflow.repository.PurchaseRepository;

@Service
public class PurchaseItemService {

    private final PurchaseItemRepository purchaseItemRepository;
    private final PurchaseRepository purchaseRepository;
    private final InventoryService inventoryService;

    public PurchaseItemService(
            PurchaseItemRepository purchaseItemRepository,
            PurchaseRepository purchaseRepository,
            InventoryService inventoryService) {

        this.purchaseItemRepository = purchaseItemRepository;
        this.purchaseRepository = purchaseRepository;
        this.inventoryService = inventoryService;
    }

    // =========================
    // SAVE
    // =========================
    public PurchaseItem savePurchaseItem(PurchaseItem purchaseItem) {

        // Validate quantity
        if (purchaseItem.getQuantity() <= 0) {
            throw new RuntimeException("Quantity must be greater than 0");
        }

        // Find the purchase
        Purchase purchase = purchaseRepository
                .findById(purchaseItem.getPurchaseId())
                .orElseThrow(() ->
                        new RuntimeException("Purchase Not Found"));

        // Get branch from purchase
        int branchId = purchase.getBranchId();

        // Increase inventory stock
        inventoryService.increaseStock(
                purchaseItem.getProductId(),
                branchId,
                purchaseItem.getQuantity()
        );

        // Save purchase item
        return purchaseItemRepository.save(purchaseItem);
    }

    // =========================
    // GET ALL
    // =========================
    public List<PurchaseItem> getAllPurchaseItems() {
        return purchaseItemRepository.findAll();
    }

    // =========================
    // GET BY ID
    // =========================
    public Optional<PurchaseItem> getPurchaseItemById(int id) {
        return purchaseItemRepository.findById(id);
    }

    // =========================
    // UPDATE
    // =========================
    public PurchaseItem updatePurchaseItem(
            int id,
            PurchaseItem purchaseItem) {

        PurchaseItem existingPurchaseItem =
                purchaseItemRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Purchase Item Not Found"));

        existingPurchaseItem.setPurchaseId(
                purchaseItem.getPurchaseId());

        existingPurchaseItem.setProductId(
                purchaseItem.getProductId());

        existingPurchaseItem.setQuantity(
                purchaseItem.getQuantity());

        existingPurchaseItem.setPurchasePrice(
                purchaseItem.getPurchasePrice());

        existingPurchaseItem.setTotalPrice(
                purchaseItem.getTotalPrice());

        return purchaseItemRepository.save(existingPurchaseItem);
    }

    // =========================
    // DELETE
    // =========================
    public String deletePurchaseItem(int id) {

        if (purchaseItemRepository.existsById(id)) {

            purchaseItemRepository.deleteById(id);

            return "Purchase Item Deleted Successfully";
        }

        return "Purchase Item Not Found";
    }
}