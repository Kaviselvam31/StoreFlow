package com.storeflow.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.storeflow.entity.Customer;
import com.storeflow.repository.CustomerRepository;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    // SAVE CUSTOMER
    public Customer saveCustomer(Customer customer) {

        customer.setStatus("ACTIVE");

        if (customer.getOfferCardStatus() == null ||
            customer.getOfferCardStatus().isEmpty()) {

            customer.setOfferCardStatus("Inactive");
        }

        return customerRepository.save(customer);
    }

    // GET ALL CUSTOMERS
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    // GET CUSTOMER BY ID
    public Optional<Customer> getCustomerById(int id) {
        return customerRepository.findById(id);
    }

    // UPDATE CUSTOMER
    public Customer updateCustomer(int id, Customer customer) {

        Customer existingCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer Not Found"));

        existingCustomer.setCustomerName(customer.getCustomerName());
        existingCustomer.setPhoneNumber(customer.getPhoneNumber());
        existingCustomer.setEmail(customer.getEmail());
        existingCustomer.setGender(customer.getGender());
        existingCustomer.setCity(customer.getCity());
        existingCustomer.setLoyaltyPoints(customer.getLoyaltyPoints());
        existingCustomer.setOfferCardStatus(customer.getOfferCardStatus());
        existingCustomer.setStatus(customer.getStatus());

        return customerRepository.save(existingCustomer);
    }

    // DELETE CUSTOMER (Soft Delete)
    public String deleteCustomer(int id) {

        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer Not Found"));

        customer.setStatus("INACTIVE");

        customerRepository.save(customer);

        return "Customer Deleted Successfully";
    }

}