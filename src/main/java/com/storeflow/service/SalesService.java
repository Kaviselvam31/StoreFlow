package com.storeflow.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.storeflow.entity.Sales;
import com.storeflow.repository.SalesRepository;

@Service
public class SalesService {

    private final SalesRepository salesRepository;

    public SalesService(SalesRepository salesRepository) {
        this.salesRepository = salesRepository;
    }

    // SAVE
    public Sales saveSale(Sales sales) {
        return salesRepository.save(sales);
    }

    // GET ALL
    public List<Sales> getAllSales() {
        return salesRepository.findAll();
    }

    // GET BY ID
    public Optional<Sales> getSaleById(int id) {
        return salesRepository.findById(id);
    }

    // UPDATE
    public Sales updateSale(int id, Sales sales) {

        Sales existingSale = salesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sale Not Found"));

        existingSale.setCustomerId(sales.getCustomerId());
        existingSale.setBranchId(sales.getBranchId());
        existingSale.setEmployeeId(sales.getEmployeeId());
        existingSale.setSaleDate(sales.getSaleDate());
        existingSale.setTotalAmount(sales.getTotalAmount());
        existingSale.setPaymentMethod(sales.getPaymentMethod());
        existingSale.setOfferDiscount(sales.getOfferDiscount());
        existingSale.setFinalAmount(sales.getFinalAmount());

        return salesRepository.save(existingSale);
    }

    // DELETE
    public String deleteSale(int id) {

        if (salesRepository.existsById(id)) {
            salesRepository.deleteById(id);
            return "Sale Deleted Successfully";
        }

        return "Sale Not Found";
    }

    // BRANCH-WISE SALES
    public List<Object[]> getBranchWiseSales() {
        return salesRepository.getBranchWiseSales();
    }
    // HIGHEST-SELLING BRANCH
public Object[] getHighestSellingBranch() {

    List<Object[]> results = salesRepository.getHighestSellingBranch();

    if (results.isEmpty()) {
        return null;
    }

    return results.get(0);
}
}