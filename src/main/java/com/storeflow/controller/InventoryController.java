package com.storeflow.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.*;

import com.storeflow.entity.Inventory;
import com.storeflow.service.InventoryService;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    // SAVE
    @PostMapping
    public Inventory saveInventory(@RequestBody Inventory inventory) {
        return inventoryService.saveInventory(inventory);
    }

    // GET ALL
    @GetMapping
    public List<Inventory> getAllInventory() {
        return inventoryService.getAllInventory();
    }

    // UPDATE
    @PutMapping("/{id}")
    public Inventory updateInventory(@PathVariable int id,
                                     @RequestBody Inventory inventory) {
        return inventoryService.updateInventory(id, inventory);
    }

    // GET BY ID
    @GetMapping("/{id}")
    public Optional<Inventory> getInventoryById(@PathVariable int id) {
        return inventoryService.getInventoryById(id);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public String deleteInventory(@PathVariable int id) {
        return inventoryService.deleteInventory(id);
    }
}