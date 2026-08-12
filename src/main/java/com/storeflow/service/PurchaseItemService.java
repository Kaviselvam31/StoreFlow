package com.storeflow.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.storeflow.entity.PurchaseItem;
import com.storeflow.repository.PurchaseItemRepository;

@Service
public class PurchaseItemService {

    private final PurchaseItemRepository purchaseItemRepository;

    public PurchaseItemService(PurchaseItemRepository purchaseItemRepository) {
        this.purchaseItemRepository = purchaseItemRepository;
    }

    // SAVE
    public PurchaseItem savePurchaseItem(PurchaseItem purchaseItem) {
        return purchaseItemRepository.save(purchaseItem);
    }

    // GET ALL
    public List<PurchaseItem> getAllPurchaseItems() {
        return purchaseItemRepository.findAll();
    }

    // GET BY ID
    public Optional<PurchaseItem> getPurchaseItemById(int id) {
        return purchaseItemRepository.findById(id);
    }

    // UPDATE
    public PurchaseItem updatePurchaseItem(int id, PurchaseItem purchaseItem) {

        PurchaseItem existingPurchaseItem = purchaseItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Purchase Item Not Found"));

        existingPurchaseItem.setPurchaseId(purchaseItem.getPurchaseId());
        existingPurchaseItem.setProductId(purchaseItem.getProductId());
        existingPurchaseItem.setQuantity(purchaseItem.getQuantity());
        existingPurchaseItem.setPurchasePrice(purchaseItem.getPurchasePrice());
        existingPurchaseItem.setTotalPrice(purchaseItem.getTotalPrice());

        return purchaseItemRepository.save(existingPurchaseItem);
    }

    // DELETE
    public String deletePurchaseItem(int id) {

        if (purchaseItemRepository.existsById(id)) {
            purchaseItemRepository.deleteById(id);
            return "Purchase Item Deleted Successfully";
        }

        return "Purchase Item Not Found";
    }
}