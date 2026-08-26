package com.storeflow.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.storeflow.entity.Inventory;
import com.storeflow.repository.InventoryRepository;

@Service
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    // =========================
    // SAVE
    // =========================
    public Inventory saveInventory(Inventory inventory) {
        return inventoryRepository.save(inventory);
    }

    // =========================
    // GET ALL
    // =========================
    public List<Inventory> getAllInventory() {
        return inventoryRepository.findAll();
    }

    // =========================
    // GET LOW STOCK INVENTORY
    // =========================
    public List<Inventory> getLowStockInventory() {
        return inventoryRepository.getLowStockInventory();
    }

    // =========================
    // GET BY ID
    // =========================
    public Optional<Inventory> getInventoryById(int id) {
        return inventoryRepository.findById(id);
    }

    // =========================
    // REDUCE STOCK AFTER SALE
    // =========================
    public void reduceStock(
            int productId,
            int branchId,
            int quantity) {

        Inventory inventory = inventoryRepository
                .findByProductIdAndBranchId(productId, branchId);

        // Check inventory exists
        if (inventory == null) {
            throw new RuntimeException("Inventory Not Found");
        }

        // Check sufficient stock
        if (inventory.getAvailableStock() < quantity) {
            throw new RuntimeException(
                    "Insufficient Stock. Available Stock: "
                    + inventory.getAvailableStock()
            );
        }

        // Reduce stock
        int remainingStock =
                inventory.getAvailableStock() - quantity;

        inventory.setAvailableStock(remainingStock);

        // Update stock status
        if (remainingStock == 0) {
            inventory.setStockStatus("OUT OF STOCK");
        } else if (remainingStock <= inventory.getReorderLevel()) {
            inventory.setStockStatus("LOW STOCK");
        } else {
            inventory.setStockStatus("AVAILABLE");
        }

        // Update date
        inventory.setStockLastUpdate(LocalDate.now());

        // Save updated inventory
        inventoryRepository.save(inventory);
    }

    // =========================
    // INCREASE STOCK AFTER PURCHASE
    // =========================
    public void increaseStock(
            int productId,
            int branchId,
            int quantity) {

        Inventory inventory = inventoryRepository
                .findByProductIdAndBranchId(productId, branchId);

        // Check inventory exists
        if (inventory == null) {
            throw new RuntimeException("Inventory Not Found");
        }

        // Validate quantity
        if (quantity <= 0) {
            throw new RuntimeException(
                    "Purchase Quantity Must Be Greater Than 0"
            );
        }

        // Increase stock
        int newStock =
                inventory.getAvailableStock() + quantity;

        inventory.setAvailableStock(newStock);

        // Update stock status
        if (newStock == 0) {
            inventory.setStockStatus("OUT OF STOCK");
        } else if (newStock <= inventory.getReorderLevel()) {
            inventory.setStockStatus("LOW STOCK");
        } else {
            inventory.setStockStatus("AVAILABLE");
        }

        // Update date
        inventory.setStockLastUpdate(LocalDate.now());

        // Save updated inventory
        inventoryRepository.save(inventory);
    }

    // =========================
    // UPDATE
    // =========================
    public Inventory updateInventory(
            int id,
            Inventory inventory) {

        Inventory existingInventory =
                inventoryRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Inventory Not Found"));

        existingInventory.setProductId(
                inventory.getProductId());

        existingInventory.setBranchId(
                inventory.getBranchId());

        existingInventory.setAvailableStock(
                inventory.getAvailableStock());

        existingInventory.setReorderLevel(
                inventory.getReorderLevel());

        existingInventory.setStockLastUpdate(
                inventory.getStockLastUpdate());

        existingInventory.setStockStatus(
                inventory.getStockStatus());

        return inventoryRepository.save(existingInventory);
    }

    // =========================
    // DELETE
    // =========================
    public String deleteInventory(int id) {

        if (inventoryRepository.existsById(id)) {

            inventoryRepository.deleteById(id);

            return "Inventory Deleted Successfully";
        }

        return "Inventory Not Found";
    }
}