package com.storeflow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.storeflow.entity.Branch;

public interface BranchRepository extends JpaRepository<Branch, Integer> {

}
