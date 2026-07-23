package com.storeflow.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.storeflow.repository.BranchRepository;

@Service
public class BranchService {

    @Autowired
    private BranchRepository branchRepository;

}
