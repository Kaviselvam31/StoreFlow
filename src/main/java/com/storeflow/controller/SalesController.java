package com.storeflow.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.storeflow.dto.BranchSalesResponse;
import com.storeflow.entity.Sales;
import com.storeflow.service.SalesService;

@RestController
@RequestMapping("/sales")
public class SalesController {

    private final SalesService salesService;

    public SalesController(SalesService salesService) {
        this.salesService = salesService;
    }

    // =========================
    // SAVE SALE
    // =========================
    @PostMapping
    public Sales saveSale(@RequestBody Sales sales) {
        return salesService.saveSale(sales);
    }

    // =========================
    // GET ALL SALES
    // =========================
    @GetMapping
    public List<Sales> getAllSales() {
        return salesService.getAllSales();
    }

    // =========================
    // GET SALE BY ID
    // =========================
    @GetMapping("/{id}")
    public Optional<Sales> getSaleById(
            @PathVariable int id) {

        return salesService.getSaleById(id);
    }

    // =========================
    // UPDATE SALE
    // =========================
    @PutMapping("/{id}")
    public Sales updateSale(
            @PathVariable int id,
            @RequestBody Sales sales) {

        return salesService.updateSale(id, sales);
    }

    // =========================
    // DELETE SALE
    // =========================
    @DeleteMapping("/{id}")
    public String deleteSale(
            @PathVariable int id) {

        return salesService.deleteSale(id);
    }

    // =========================
    // BRANCH-WISE SALES
    // =========================
    @GetMapping("/branch-wise")
    public List<BranchSalesResponse> getBranchWiseSales() {

        return salesService.getBranchWiseSales();
    }

    // =========================
    // HIGHEST-SELLING BRANCH
    // =========================
    @GetMapping("/highest-branch")
    public Object[] getHighestSellingBranch() {

        return salesService.getHighestSellingBranch();
    }
}