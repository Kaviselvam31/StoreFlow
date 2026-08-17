package com.storeflow.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.storeflow.entity.Inventory;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Integer> {

    // LOW STOCK PRODUCTS
    @Query("SELECT i FROM Inventory i " +
           "WHERE i.availableStock <= i.reorderLevel")
    List<Inventory> getLowStockInventory();

    // FIND INVENTORY BY PRODUCT AND BRANCH
    @Query("SELECT i FROM Inventory i " +
           "WHERE i.productId = :productId " +
           "AND i.branchId = :branchId")
    Inventory findByProductIdAndBranchId(
            @Param("productId") int productId,
            @Param("branchId") int branchId
    );
}