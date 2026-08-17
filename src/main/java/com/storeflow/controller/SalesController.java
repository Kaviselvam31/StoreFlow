package com.storeflow.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.*;

import com.storeflow.entity.Sales;
import com.storeflow.service.SalesService;

@RestController
@RequestMapping("/sales")
public class SalesController {

    private final SalesService salesService;

    public SalesController(SalesService salesService) {
        this.salesService = salesService;
    }

    // SAVE
    @PostMapping
    public Sales saveSale(@RequestBody Sales sales) {
        return salesService.saveSale(sales);
    }

    // GET ALL
    @GetMapping
    public List<Sales> getAllSales() {
        return salesService.getAllSales();
    }

    // GET BY ID
    @GetMapping("/{id}")
    public Optional<Sales> getSaleById(@PathVariable int id) {
        return salesService.getSaleById(id);
    }

    // UPDATE
    @PutMapping("/{id}")
    public Sales updateSale(
            @PathVariable int id,
            @RequestBody Sales sales) {

        return salesService.updateSale(id, sales);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public String deleteSale(@PathVariable int id) {
        return salesService.deleteSale(id);
    }
    // BRANCH-WISE SALES
@GetMapping("/branch-wise")
public List<Object[]> getBranchWiseSales() {
    return salesService.getBranchWiseSales();
}
// HIGHEST-SELLING BRANCH
@GetMapping("/highest-branch")
public Object[] getHighestSellingBranch() {
    return salesService.getHighestSellingBranch();
}
}