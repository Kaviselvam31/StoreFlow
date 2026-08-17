package com.storeflow.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.storeflow.entity.Sales;

@Repository
public interface SalesRepository extends JpaRepository<Sales, Integer> {

    @Query("SELECT s.branchId, SUM(s.finalAmount) " +
           "FROM Sales s " +
           "GROUP BY s.branchId")
    List<Object[]> getBranchWiseSales();
    @Query("SELECT s.branchId, SUM(s.finalAmount) " +
       "FROM Sales s " +
       "GROUP BY s.branchId " +
       "ORDER BY SUM(s.finalAmount) DESC")
List<Object[]> getHighestSellingBranch();
}