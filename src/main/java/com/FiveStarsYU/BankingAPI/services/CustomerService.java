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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {


    @Autowired
    private CustomerRepo customerRepository;
    @Autowired
    AccountServices accountServices;

    public Customer createCustomer(Customer customer){

        return customerRepository.save(customer);
    }

    public List<Customer> getAllCustomers() {

        List<Customer> listOfCustomers = new ArrayList<>();
        customerRepository.findAll().forEach(listOfCustomers::add);
        return listOfCustomers;
    }

    public Optional<Customer> getCustomerByAccountId(Long account_id) {

        Long customerId = accountServices.getAccountByAccountId(account_id).get().getId();
        return customerRepository.findById(customerId);
    }

    public Optional<Customer> getCustomerByCustomerId(Long id) {
        return customerRepository.findById(id);
    }

    public boolean customerCheck(Long customerId){
        Customer customer = customerRepository.findById(customerId).orElse(null);
        return customer != null;
    }

    public void updateCustomer(Customer customer) {

        customerRepository.save(customer);
    }

}
