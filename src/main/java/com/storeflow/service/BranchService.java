package com.storeflow.service;

import org.springframework.stereotype.Service;

import com.storeflow.entity.Branch;
import com.storeflow.repository.BranchRepository;

@Service
public class BranchService {

    private final BranchRepository branchRepository;

    public BranchService(BranchRepository branchRepository) {
        this.branchRepository = branchRepository;
    }
public Branch saveBranch(Branch branch) {
    return branchRepository.save(branch);
}
}