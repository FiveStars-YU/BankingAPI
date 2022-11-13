package com.FiveStarsYU.BankingAPI.controllers;

import com.FiveStarsYU.BankingAPI.errorhandling.CodeMessage;
import com.FiveStarsYU.BankingAPI.errorhandling.CodeMessageData;
import com.FiveStarsYU.BankingAPI.models.Account;
import com.FiveStarsYU.BankingAPI.services.AccountServices;
import org.aspectj.apache.bcel.classfile.Code;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class AccountController {

    @Autowired
    private AccountServices accountServices;

    //get all accounts
    @GetMapping("/accounts")
    public ResponseEntity<?> getAllAccounts(){
        Iterable<Account> accounts = accountServices.getAllAccounts();

        if (accounts.iterator().hasNext()){
            CodeMessageData successfullResponse = new CodeMessageData(200,"Success", accounts);
            return new ResponseEntity<>(successfullResponse, HttpStatus.OK);
        }

        CodeMessage failedResponse = new CodeMessage(404,"Error fetching accounts");
        return new ResponseEntity<>(failedResponse,HttpStatus.NOT_FOUND);
    }

    //get account by customer id
    @GetMapping("/accounts/{accountId}")
    public ResponseEntity<?> getAccountByAccountId(@PathVariable Long accountId){
        Optional<Account> accounts = accountServices.getAccountByAccountId(accountId);
        if (accounts.isEmpty()){
            CodeMessage failedResponse = new CodeMessage(404,"Error fetching accounts");
            return new ResponseEntity<>(failedResponse,HttpStatus.NOT_FOUND);
        }
        CodeMessageData successfullResponse = new CodeMessageData(200,"Success", accounts);
        return new ResponseEntity<>(successfullResponse,HttpStatus.OK);
    }

    //get all accounts for customer
    @GetMapping("/customers/{customerId}/accounts")
    public ResponseEntity<?> getAllAccountsByCustomerId(@PathVariable Long customerId){
        Iterable<Account> accounts = accountServices.getAllAccountsByCustomerId(customerId);
        if (accounts.iterator().hasNext()){
            CodeMessageData successfullResponse = new CodeMessageData(200,"Success", accounts);
            return new ResponseEntity<>(successfullResponse,HttpStatus.OK);
        }
        CodeMessage failedResponse = new CodeMessage(404,"Error fetching accounts");
        return new ResponseEntity<>(failedResponse,HttpStatus.NOT_FOUND);
    }

    //update account
    @PutMapping("/accounts/{accountId}")
    public ResponseEntity<?> updateAccount(@PathVariable Long accountId, @RequestBody Account account){
        if (accountServices.accountCheck(accountId)){
            accountServices.updateAccount(account);
            CodeMessage successfullResponse = new CodeMessage(200,"Success");
            return new ResponseEntity<>(successfullResponse,HttpStatus.OK);
        }
        CodeMessage failedResponse = new CodeMessage(404,"Error");
        return new ResponseEntity<>(failedResponse,HttpStatus.NOT_FOUND);
    }

    //create account
    @PostMapping("/customers/{customerId}/accounts")
    public ResponseEntity<?> createAccount(@PathVariable Long customerId, @RequestBody Account account){
        try {
            if (accountServices.customerCheck(customerId)){
                CodeMessageData successfullResponse = new CodeMessageData(200,"Account created", accountServices.createAccount(account));
                return new ResponseEntity<>(successfullResponse,HttpStatus.OK);
            }
            CodeMessage failedResponse = new CodeMessage(404,"Error creating account");
            return new ResponseEntity<>(failedResponse,HttpStatus.NOT_FOUND);

        } catch (Exception exception){
            CodeMessage failedResponse = new CodeMessage(404,"Error creating account");
            return new ResponseEntity<>(failedResponse,HttpStatus.NOT_FOUND);
        }
    }

    //delete account
    @DeleteMapping("/accounts/{accountId}")
    public ResponseEntity<?> deleteAccount(@PathVariable Long accountId){
        if (accountServices.accountCheck(accountId)){
            accountServices.deleteAccountByAccountId(accountId);
            CodeMessage successfullResponse = new CodeMessage(200,"Account successfully deleted");
            return new ResponseEntity<>(successfullResponse,HttpStatus.OK);
        }
        CodeMessage failedResponse = new CodeMessage(404,"Account does not exist");
        return new ResponseEntity<>(failedResponse,HttpStatus.NOT_FOUND);
    }
}
