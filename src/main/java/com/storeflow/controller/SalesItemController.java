package com.storeflow.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.*;

import com.storeflow.entity.SalesItem;
import com.storeflow.service.SalesItemService;

@RestController
@RequestMapping("/sales-item")
public class SalesItemController {

    private final SalesItemService salesItemService;

    public SalesItemController(SalesItemService salesItemService) {
        this.salesItemService = salesItemService;
    }

    // SAVE
    @PostMapping
    public SalesItem saveSalesItem(@RequestBody SalesItem salesItem) {
        return salesItemService.saveSalesItem(salesItem);
    }

    // GET ALL
    @GetMapping
    public List<SalesItem> getAllSalesItems() {
        return salesItemService.getAllSalesItems();
    }

    // GET BY ID
    @GetMapping("/{id}")
    public Optional<SalesItem> getSalesItemById(@PathVariable int id) {
        return salesItemService.getSalesItemById(id);
    }

    // UPDATE
    @PutMapping("/{id}")
    public SalesItem updateSalesItem(
            @PathVariable int id,
            @RequestBody SalesItem salesItem) {

        return salesItemService.updateSalesItem(id, salesItem);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public String deleteSalesItem(@PathVariable int id) {
        return salesItemService.deleteSalesItem(id);
    }
}