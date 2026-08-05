package com.storeflow.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.*;

import com.storeflow.entity.Customer;
import com.storeflow.service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    // SAVE CUSTOMER
    @PostMapping
    public Customer saveCustomer(@RequestBody Customer customer) {
        return customerService.saveCustomer(customer);
    }

    // GET ALL CUSTOMERS
    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    // GET CUSTOMER BY ID
    @GetMapping("/{id}")
    public Optional<Customer> getCustomerById(@PathVariable int id) {
        return customerService.getCustomerById(id);
    }

    // UPDATE CUSTOMER
    @PutMapping("/{id}")
    public Customer updateCustomer(@PathVariable int id,
                                   @RequestBody Customer customer) {
        return customerService.updateCustomer(id, customer);
    }

    // DELETE CUSTOMER (Soft Delete)
    @DeleteMapping("/{id}")
    public String deleteCustomer(@PathVariable int id) {
        return customerService.deleteCustomer(id);
    }
}