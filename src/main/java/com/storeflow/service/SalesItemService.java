package com.storeflow.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.storeflow.entity.Sales;
import com.storeflow.entity.SalesItem;
import com.storeflow.repository.SalesItemRepository;
import com.storeflow.repository.SalesRepository;

@Service
public class SalesItemService {

    private final SalesItemRepository salesItemRepository;
    private final SalesRepository salesRepository;
    private final InventoryService inventoryService;

    public SalesItemService(
            SalesItemRepository salesItemRepository,
            SalesRepository salesRepository,
            InventoryService inventoryService) {

        this.salesItemRepository = salesItemRepository;
        this.salesRepository = salesRepository;
        this.inventoryService = inventoryService;
    }

    // SAVE
    @Transactional
    public SalesItem saveSalesItem(SalesItem salesItem) {

        // 1. Validate quantity
        if (salesItem.getQuantity() <= 0) {
            throw new RuntimeException("Quantity must be greater than 0");
        }

        // 2. Find Sale
        Sales sales = salesRepository.findById(salesItem.getSaleId())
                .orElseThrow(() ->
                        new RuntimeException("Sale Not Found"));

        // 3. Get branch from Sale
        int branchId = sales.getBranchId();

        // 4. Reduce inventory stock
        inventoryService.reduceStock(
                salesItem.getProductId(),
                branchId,
                salesItem.getQuantity()
        );

        // 5. Save SalesItem
        return salesItemRepository.save(salesItem);
    }

    // GET ALL
    public List<SalesItem> getAllSalesItems() {
        return salesItemRepository.findAll();
    }

    // GET BY ID
    public Optional<SalesItem> getSalesItemById(int id) {
        return salesItemRepository.findById(id);
    }

    // UPDATE
    public SalesItem updateSalesItem(int id, SalesItem salesItem) {

        SalesItem existingSalesItem = salesItemRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Sales Item Not Found"));

        existingSalesItem.setSaleId(salesItem.getSaleId());
        existingSalesItem.setProductId(salesItem.getProductId());
        existingSalesItem.setQuantity(salesItem.getQuantity());
        existingSalesItem.setSellingPrice(salesItem.getSellingPrice());
        existingSalesItem.setDiscount(salesItem.getDiscount());
        existingSalesItem.setTotalPrice(salesItem.getTotalPrice());

        return salesItemRepository.save(existingSalesItem);
    }

    // DELETE
    public String deleteSalesItem(int id) {

        if (salesItemRepository.existsById(id)) {
            salesItemRepository.deleteById(id);
            return "Sales Item Deleted Successfully";
        }

        return "Sales Item Not Found";
    }
}