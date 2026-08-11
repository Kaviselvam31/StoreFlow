package com.storeflow.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.storeflow.entity.Purchase;
import com.storeflow.repository.PurchaseRepository;

@Service
public class PurchaseService {

    private final PurchaseRepository purchaseRepository;

    public PurchaseService(PurchaseRepository purchaseRepository) {
        this.purchaseRepository = purchaseRepository;
    }

    // SAVE
    public Purchase savePurchase(Purchase purchase) {
        return purchaseRepository.save(purchase);
    }

    // GET ALL
    public List<Purchase> getAllPurchases() {
        return purchaseRepository.findAll();
    }

    // GET BY ID
    public Optional<Purchase> getPurchaseById(int id) {
        return purchaseRepository.findById(id);
    }

    // UPDATE
    public Purchase updatePurchase(int id, Purchase purchase) {

        Purchase existingPurchase = purchaseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Purchase Not Found"));

        existingPurchase.setSupplierId(purchase.getSupplierId());
        existingPurchase.setBranchId(purchase.getBranchId());
        existingPurchase.setPurchaseDate(purchase.getPurchaseDate());
        existingPurchase.setTotalAmount(purchase.getTotalAmount());
        existingPurchase.setPaymentStatus(purchase.getPaymentStatus());
        existingPurchase.setStatus(purchase.getStatus());

        return purchaseRepository.save(existingPurchase);
    }

    // DELETE
    public String deletePurchase(int id) {

        if (purchaseRepository.existsById(id)) {
            purchaseRepository.deleteById(id);
            return "Purchase Deleted Successfully";
        }

        return "Purchase Not Found";
    }
}