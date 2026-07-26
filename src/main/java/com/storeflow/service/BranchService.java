package com.storeflow.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.storeflow.entity.Branch;
import com.storeflow.repository.BranchRepository;

@Service
public class BranchService {

    // Inject Repository
    private final BranchRepository branchRepository;

    // Constructor Injection
    public BranchService(BranchRepository branchRepository) {
        this.branchRepository = branchRepository;
    }

    // Save Branch (POST API)
    public Branch saveBranch(Branch branch) {
        return branchRepository.save(branch);
    }

    // Get All Branches (GET API)
    public List<Branch> getAllBranches() {
        return branchRepository.findAll();
    }
}