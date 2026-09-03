package com.storeflow.dto;

import java.math.BigDecimal;

public class BranchSalesResponse {

    private int branchId;
    private BigDecimal totalSales;

    public BranchSalesResponse() {
    }

    public BranchSalesResponse(int branchId, BigDecimal totalSales) {
        this.branchId = branchId;
        this.totalSales = totalSales;
    }

    public int getBranchId() {
        return branchId;
    }

    public void setBranchId(int branchId) {
        this.branchId = branchId;
    }

    public BigDecimal getTotalSales() {
        return totalSales;
    }

    public void setTotalSales(BigDecimal totalSales) {
        this.totalSales = totalSales;
    }
}