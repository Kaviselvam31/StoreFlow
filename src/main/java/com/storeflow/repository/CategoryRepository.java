package com.storeflow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.storeflow.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

}