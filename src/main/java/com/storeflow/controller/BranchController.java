package com.storeflow.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
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

    public BranchController(BranchService branchService) {
        this.branchService = branchService;
    }

    // POST API
    @PostMapping
    public Branch saveBranch(@RequestBody Branch branch) {
        return branchService.saveBranch(branch);
    }

    // GET API
    @GetMapping
    public List<Branch> getAllBranches() {
        return branchService.getAllBranches();
    }
}