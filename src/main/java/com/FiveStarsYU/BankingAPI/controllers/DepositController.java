package com.FiveStarsYU.BankingAPI.controllers;

import com.FiveStarsYU.BankingAPI.models.Customer;
import com.FiveStarsYU.BankingAPI.models.Deposit;
import com.FiveStarsYU.BankingAPI.repository.DepositRepo;
import com.FiveStarsYU.BankingAPI.services.DepositService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class DepositController {

    @Autowired
    private DepositRepo depositRepo;
    @Autowired
    private DepositService depositService;

    @PostMapping("/deposit/{customerId}/deposit")
    public void createDeposit(@PathVariable Long customerId, @RequestBody Customer customer){
        depositService.createDeposit(accountId,deposit);
    }
    @GetMapping("/deposit/{accountId}/deposit")
    public Iterable<Deposit> getAllDepositsByAccountId(@PathVariable Long accountId){
        return depositService.getAllDepositsByAccountId(accountId);
    }
    @GetMapping("/deposit/{depositId}")
    public ResponseEntity<?> getDepositById(@PathVariable Long depositId){
        return depositService.getDepositByDepositId(depositId);
    }
    @DeleteMapping("/deposit/{depositId}")
    public void deleteDepositById(@PathVariable Long depositId){
        depositService.deleteDepositById(depositId);
    }
}
