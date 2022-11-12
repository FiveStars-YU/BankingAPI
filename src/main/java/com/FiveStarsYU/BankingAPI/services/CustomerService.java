package com.FiveStarsYU.BankingAPI.services;


import com.FiveStarsYU.BankingAPI.models.Address;
import com.FiveStarsYU.BankingAPI.models.Customer;
import com.FiveStarsYU.BankingAPI.repository.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {


    @Autowired
    CustomerRepo customerRepo;

//    public void verifyCustomer(Long id){
//        Customer customer= customerRepo.findById(id).orElse(null);
//    }

    public void addCustomer(Customer customer){
        customerRepo.save(customer);
    }

    public ResponseEntity<Iterable<Customer>> getAllCustomer(){
        Iterable<Customer> allCustomer= customerRepo.findAll();
        return new ResponseEntity<>(allCustomer, HttpStatus.OK);
    }


    public Optional<Customer> getCustomerById(Long id){

        return customerRepo.findById(id);
    }

    public ResponseEntity<?> updateCustomer(Customer customer, Long id) {
        Customer c = customerRepo.save(customer);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public void deleteCustomerById(Long id) {

        customerRepo.deleteById(id);
    }



}
