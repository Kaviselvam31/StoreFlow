package com.storeflow.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/branch")
public class BranchController {

    @GetMapping("/hello")
    public String hello() {
        return "Welcome to StoreFlow";
    }

}