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


