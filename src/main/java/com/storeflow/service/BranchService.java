package com.storeflow.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.storeflow.entity.Branch;
import com.storeflow.repository.BranchRepository;

@Service
public class BranchService {

    private final BranchRepository branchRepository;

    public BranchService(BranchRepository branchRepository) {
        this.branchRepository = branchRepository;
    }

    // POST API
    public Branch saveBranch(Branch branch) {
        return branchRepository.save(branch);
    }

    // GET ALL API
    public List<Branch> getAllBranches() {
        return branchRepository.findAll();
    }

    // GET BY ID API
    public Optional<Branch> getBranchById(int id) {
        return branchRepository.findById(id);
    }

    // UPDATE API
    public Branch updateBranch(int id, Branch updatedBranch) {

        Branch branch = branchRepository.findById(id).orElse(null);

        if (branch != null) {
            branch.setBranchName(updatedBranch.getBranchName());
            branch.setCity(updatedBranch.getCity());
            branch.setState(updatedBranch.getState());

            return branchRepository.save(branch);
        }

        return null;
    }

    // DELETE API
    public void deleteBranch(int id) {
        branchRepository.deleteById(id);
    }
}