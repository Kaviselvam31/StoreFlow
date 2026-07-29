package com.storeflow.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.*;

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

    // GET ALL API
    @GetMapping
    public List<Branch> getAllBranches() {
        return branchService.getAllBranches();
    }

    // GET BY ID API
    @GetMapping("/{id}")
    public Optional<Branch> getBranchById(@PathVariable int id) {
        return branchService.getBranchById(id);
    }

    // UPDATE API
    @PutMapping("/{id}")
    public Branch updateBranch(@PathVariable int id,
                               @RequestBody Branch branch) {
        return branchService.updateBranch(id, branch);
    }

    // DELETE API
    @DeleteMapping("/{id}")
    public String deleteBranch(@PathVariable int id) {

        branchService.deleteBranch(id);

        return "Branch Deleted Successfully!";
    }
}