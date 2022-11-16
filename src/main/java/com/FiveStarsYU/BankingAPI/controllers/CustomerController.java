package com.FiveStarsYU.BankingAPI.controllers;

import com.FiveStarsYU.BankingAPI.errorhandling.CodeMessage;
import com.FiveStarsYU.BankingAPI.errorhandling.CodeMessageData;
import com.FiveStarsYU.BankingAPI.models.Account;
import com.FiveStarsYU.BankingAPI.models.Address;
import com.FiveStarsYU.BankingAPI.models.Customer;
import com.FiveStarsYU.BankingAPI.services.AccountServices;
import com.FiveStarsYU.BankingAPI.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/customers")
    public ResponseEntity<?> createCustomer(@RequestBody Customer customer) {

        try {
            CodeMessageData response = new CodeMessageData(200, "Customer account created", customerService.createCustomer(customer));
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            CodeMessage error = new CodeMessage(404, "Error creating customer");
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/customers")
    public ResponseEntity<?> getAllCustomers() {
        List<Customer> p = customerService.getAllCustomers();
        if (p.isEmpty()) {
            CodeMessage error = new CodeMessage(404, "Error fetching customers");
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }

        CodeMessageData response = new CodeMessageData(200, "Success", p);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/accounts/{account_id}/customer")
    public ResponseEntity<?> getCustomerByAccount(@PathVariable Long account_id) {
        Customer p = customerService.getCustomerByAccountId(account_id).orElse(null);
        if (p == null) {
            CodeMessage error = new CodeMessage(0, "error getting customer");
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }

        CodeMessageData response = new CodeMessageData(200, "Success", p);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/customers/{id}")
    public ResponseEntity<?> getCustomerById(@PathVariable Long id) {
        Customer p = customerService.getCustomerByCustomerId(id).orElse(null);
        if (p == null) {
            CodeMessage error = new CodeMessage(404, "error fetching customer");
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }

        CodeMessageData response = new CodeMessageData(200, "Success", p);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/customers/{id}")
    public ResponseEntity<?> updateCustomer(@RequestBody Customer customer, @PathVariable Long id) {
        if (!customerService.customerCheck(id)) {
            CodeMessage exception = new CodeMessage("Customer ID does not exist");
            return new ResponseEntity<>(exception, HttpStatus.NOT_FOUND);
        }

        customerService.updateCustomer(customer);
        CodeMessage response = new CodeMessage(202, "Accepted customer modification");
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

}