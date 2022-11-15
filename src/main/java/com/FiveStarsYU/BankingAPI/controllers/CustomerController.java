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

import java.util.Optional;

@RestController
public class CustomerController {

    @Autowired
    private CustomerService  customerService;

    @Autowired
    private AccountServices accountServices;

    @PostMapping("/customer")
    public ResponseEntity<?> addCustomer(@PathVariable Long customerId,@RequestBody Account account){
    if(accountServices.customerCheck(customerId)){
        CodeMessageData successfullResponse = new CodeMessageData(200,"Success",  accountServices.createAccount(account));
        return new ResponseEntity<>(successfullResponse, HttpStatus.OK);
    }
       CodeMessage failedResponse= new CodeMessage(404,"Error");
        return new ResponseEntity<>(failedResponse,HttpStatus.NOT_FOUND);
    }

    @GetMapping("/customers")
    public ResponseEntity<?> getAllCustomers(){
        Iterable<Customer> customers = customerService.getAllCustomer();

        if (customers.iterator().hasNext()){
            CodeMessageData successfullResponse = new CodeMessageData(200,"Success", customers);
            return new ResponseEntity<>(successfullResponse, HttpStatus.OK);
        }

        CodeMessage failedResponse = new CodeMessage(404,"Error fetching accounts");
        return new ResponseEntity<>(failedResponse,HttpStatus.NOT_FOUND);
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<?> getCustomerById(@PathVariable Long id){
        Optional<Customer> customer = customerService.getCustomerById(id);
        if (customer.isEmpty()){
            CodeMessage failedResponse = new CodeMessage(404,"Error fetching account");
            return new ResponseEntity<>(failedResponse,HttpStatus.NOT_FOUND);
        }
        CodeMessageData successfullResponse = new CodeMessageData(200,"Success", customer);
        return new ResponseEntity<>(successfullResponse,HttpStatus.OK);
    }
    @GetMapping("/accounts/{accountId}/customer")
    public ResponseEntity<?> getCustomersAccounts(@PathVariable Long accountId){
        Customer customer=customerService.getCustomerByAccountId(accountId).orElse(null);
        if(customer.equals(null)){
            CodeMessage failedResponse = new CodeMessage(404,"Error fetching account");
            return new ResponseEntity<>(failedResponse,HttpStatus.NOT_FOUND);
        }
        CodeMessageData successfullResponse = new CodeMessageData(200,"Customer account updated", customer);
        return new ResponseEntity<>(successfullResponse,HttpStatus.OK);
    }

    @PutMapping("/customer/{id}")
    public void updateCustomer(@RequestBody Customer customer, @PathVariable Long id) {

         customerService.updateCustomer(customer,id);
    }
    @DeleteMapping("/customer/{id}")
    public void deleteCustomerById(@PathVariable Long id){

        customerService.deleteCustomerById(id);
    }





}
