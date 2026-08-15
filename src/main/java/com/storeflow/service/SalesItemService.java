package com.storeflow.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.storeflow.entity.SalesItem;
import com.storeflow.repository.SalesItemRepository;

@Service
public class SalesItemService {

    private final SalesItemRepository salesItemRepository;

    public SalesItemService(SalesItemRepository salesItemRepository) {
        this.salesItemRepository = salesItemRepository;
    }

    // SAVE
    public SalesItem saveSalesItem(SalesItem salesItem) {
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
                .orElseThrow(() -> new RuntimeException("Sales Item Not Found"));

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