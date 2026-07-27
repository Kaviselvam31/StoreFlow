package com.storeflow.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.storeflow.entity.Branch;
import com.storeflow.repository.BranchRepository;

@Service
public class BranchService {

    // Repository Injection
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

    // Get Branch By ID (GET API)
    public Optional<Branch> getBranchById(int id) {
        return branchRepository.findById(id);
    }
}