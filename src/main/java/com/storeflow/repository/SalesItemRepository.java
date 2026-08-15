package com.storeflow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.storeflow.entity.SalesItem;

@Repository
public interface SalesItemRepository extends JpaRepository<SalesItem, Integer> {

}