package com.FiveStarsYU.BankingAPI.services;


import com.FiveStarsYU.BankingAPI.models.Account;
import com.FiveStarsYU.BankingAPI.models.Address;
import com.FiveStarsYU.BankingAPI.models.Customer;
import com.FiveStarsYU.BankingAPI.repository.CustomerRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {




    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private AccountServices accountServices;


    public Customer addCustomer(Customer customer){
        return customerRepo.save(customer);
    }

    public Iterable<Customer> getAllCustomer(){
        return customerRepo.findAll();
    }

    public Optional<Customer> getCustomerById(Long id){
        return customerRepo.findById(id);
    }

    public Optional<Customer> getCustomerByAccountId(Long accountId){
        Long customerId= accountServices.getAccountByAccountId(accountId).get().getId();
        return customerRepo.findById(customerId);
    }

    public void updateCustomer(Customer customer, Long id) {
        customerRepo.save(customer);
    }

    public void deleteCustomerById(Long id) {

        customerRepo.deleteById(id);
    }

    public boolean checkCustomerById(Long customerId){
        Customer c= customerRepo.findById(customerId).orElse(null);
        return c != null;
    }


}
