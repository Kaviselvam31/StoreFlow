package com.storeflow.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "inventory")
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inventory_id")
    private int inventoryId;

    @Column(name = "product_id")
    private int productId;

    @Column(name = "branch_id")
    private int branchId;

    @Column(name = "available_stock", nullable = false)
    private int availableStock;

    @Column(name = "reorder_level")
    private int reorderLevel;

    @Column(name = "stock_last_update")
    private LocalDate stockLastUpdate;

    @Column(name = "stock_status")
    private String stockStatus;

    public Inventory() {
    }

    public int getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(int inventoryId) {
        this.inventoryId = inventoryId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getBranchId() {
        return branchId;
    }

    public void setBranchId(int branchId) {
        this.branchId = branchId;
    }

    public int getAvailableStock() {
        return availableStock;
    }

    public void setAvailableStock(int availableStock) {
        this.availableStock = availableStock;
    }

    public int getReorderLevel() {
        return reorderLevel;
    }

    public void setReorderLevel(int reorderLevel) {
        this.reorderLevel = reorderLevel;
    }

    public LocalDate getStockLastUpdate() {
        return stockLastUpdate;
    }

    public void setStockLastUpdate(LocalDate stockLastUpdate) {
        this.stockLastUpdate = stockLastUpdate;
    }

    public String getStockStatus() {
        return stockStatus;
    }

    public void setStockStatus(String stockStatus) {
        this.stockStatus = stockStatus;
    }
}