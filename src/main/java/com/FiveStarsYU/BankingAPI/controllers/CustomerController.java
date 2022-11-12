package com.FiveStarsYU.BankingAPI.controllers;

import com.FiveStarsYU.BankingAPI.models.Address;
import com.FiveStarsYU.BankingAPI.models.Customer;
import com.FiveStarsYU.BankingAPI.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class CustomerController {

    @Autowired
    private CustomerService  customerService;

    @PostMapping("/customer")
    public void addCustomer(@RequestBody Customer customer){
        customerService.addCustomer(customer);
    }

    @GetMapping("/customers")
    public ResponseEntity<?> getAllCustomers(){
        return customerService.getAllCustomer();
    }

    @GetMapping("/customer/{id}")
    public Optional<Customer> getCustomerById(@PathVariable Long id){

        return customerService.getCustomerById(id);
    }

    @PutMapping("/customer/{id}")
    public ResponseEntity<?> updateCustomer(@RequestBody Customer customer, @PathVariable Long id) {

        return customerService.updateCustomer(customer,id);
    }
    @DeleteMapping("/customer/{id}")
    public void deleteCustomerById(@PathVariable Long id){

        customerService.deleteCustomerById(id);
    }



}
