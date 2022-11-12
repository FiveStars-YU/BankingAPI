package com.FiveStarsYU.BankingAPI.controllers;

import com.FiveStarsYU.BankingAPI.models.Account;
import com.FiveStarsYU.BankingAPI.services.AccountServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

    @Autowired
    private AccountServices accountServices;

//    @GetMapping("/accounts")
//    public ResponseEntity<?> getAllAccounts(){
//        Iterable<Account> accounts = accountServices.getAllAccounts();
//        if (accounts.iterator().hasNext()){
//            return new ResponseEntity<>()
//        }
//    }
}
