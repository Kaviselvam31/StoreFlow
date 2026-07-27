package com.storeflow.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.storeflow.entity.Branch;
import com.storeflow.service.BranchService;

@RestController
@RequestMapping("/branch")
public class BranchController {

    private final BranchService branchService;

    // Constructor Injection
    public BranchController(BranchService branchService) {
        this.branchService = branchService;
    }

    // POST API - Save Branch
    @PostMapping
    public Branch saveBranch(@RequestBody Branch branch) {
        return branchService.saveBranch(branch);
    }

    // GET API - Get All Branches
    @GetMapping
    public List<Branch> getAllBranches() {
        return branchService.getAllBranches();
    }

    // GET API - Get Branch By ID
    @GetMapping("/{id}")
    public Optional<Branch> getBranchById(@PathVariable int id) {
        return branchService.getBranchById(id);
    }
}