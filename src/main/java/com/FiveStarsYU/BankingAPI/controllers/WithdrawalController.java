package com.FiveStarsYU.BankingAPI.controllers;

import com.FiveStarsYU.BankingAPI.errorhandling.CodeData;
import com.FiveStarsYU.BankingAPI.errorhandling.CodeMessage;
import com.FiveStarsYU.BankingAPI.errorhandling.CodeMessageData;
import com.FiveStarsYU.BankingAPI.models.Withdrawal;
import com.FiveStarsYU.BankingAPI.repository.AccountRepo;
import com.FiveStarsYU.BankingAPI.repository.WithdrawalRepository;
import com.FiveStarsYU.BankingAPI.services.WithdrawalServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class WithdrawalController {
    @Autowired
    private WithdrawalRepository withdrawalRepository;
    @Autowired
    private WithdrawalServices withdrawalServices;
    @Autowired
    private AccountRepo accountRepo;

    // create withdrawal
    @PostMapping("/withdrawal/{accountId}/withdrawals")
    public ResponseEntity<?> createWithdrawal(@PathVariable Long accountId, @RequestBody Withdrawal withdrawal){
        try {
            if (!withdrawalServices.accountCheck(accountId)) {
                CodeMessage failedResponse = new CodeMessage(404, "Error creating withdrawal: Account not found");
                return new ResponseEntity<>(failedResponse, HttpStatus.NOT_FOUND);
            } else if (withdrawal.getAmount() >= accountRepo.findById(accountId).get().getBalance()) {
                CodeMessage failedResponse = new CodeMessage(404, "Error creating withdrawal: Over withdrawal");
                return new ResponseEntity<>(failedResponse, HttpStatus.BAD_REQUEST);
            } else if (withdrawal.getAmount() <= 0) {
                CodeMessage failedResponse = new CodeMessage(404, "Error creating withdrawal: Withdrawal amount must be greater than zero");
                return new ResponseEntity<>(failedResponse, HttpStatus.BAD_REQUEST);
            } else {
                Withdrawal w1 = withdrawalServices.createWithdrawal(accountId, withdrawal);
                CodeMessageData successful = new CodeMessageData(201, "Created withdrawal and deducted it from the account", w1);
                return new ResponseEntity<>(successful, HttpStatus.CREATED);
            } } catch (Exception e){
            CodeMessage error = new CodeMessage(404, "Error creating withdrawal");
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }
    }
    // Get All WIthdrawals for specific account
    @GetMapping("/accounts/{accountId}/withdrawals")
    public ResponseEntity<?> getAllWithdrawalsByAccountId(@PathVariable Long accountId){
        Iterable<Withdrawal> withdrawals = withdrawalServices.getAllWithdrawalsByAccountId(accountId);
        if (withdrawals.iterator().hasNext()){
            CodeData successfulResponse = new CodeData(200, withdrawals);
            return new ResponseEntity<>(successfulResponse, HttpStatus.OK);
        }
        CodeMessage failedResponse = new CodeMessage(404, "Account not found");
        return new ResponseEntity<>(failedResponse,HttpStatus.NOT_FOUND);
    }
    // get withdrawals by id
    @GetMapping("/withdrawals/{withdrawalId}")
    public ResponseEntity<?> getWithdrawalById(@PathVariable Long withdrawalId){
        Optional<Withdrawal> withdrawals = withdrawalServices.getWithdrawalById(withdrawalId);
        if (withdrawals.isEmpty()){
            CodeData failedResponse = new CodeData(404, "error fetching withdrawal with id");
            return new ResponseEntity<>(failedResponse, HttpStatus.NOT_FOUND);
        }
        CodeData successfulResponse = new CodeData(200, withdrawals);
        return new ResponseEntity<>(successfulResponse, HttpStatus.OK);
    }
    // update existing withdrawal
    @PutMapping("/withdrawals/{withdrawalId}")
    public ResponseEntity<?> updateWithdrawal(@PathVariable Long withdrawalId, @RequestBody Withdrawal withdrawal){
        if (!withdrawalServices.withdrawalCheck(withdrawalId)){
            CodeMessage failedResponse = new CodeMessage(404, "Withdrawal ID does not exist");
            return new ResponseEntity<>(failedResponse,HttpStatus.NOT_FOUND);
        }
        withdrawalServices.updateWithdrawal(withdrawalId, withdrawal);
        CodeMessage successfulResponse = new CodeMessage(200, "Accepted withdrawal modification");
        return new ResponseEntity<>(successfulResponse, HttpStatus.OK);
    }

    @DeleteMapping("/withdrawals/{withdrawalId}")
    public ResponseEntity<?> deleteWithdrawalById(@PathVariable Long withdrawalId){
        if (!withdrawalServices.withdrawalCheck(withdrawalId)){
            CodeMessage failedResponse = new CodeMessage(404, "This id does not exist in withdrawals");
            return new ResponseEntity<>(failedResponse, HttpStatus.NOT_FOUND);
        }
        withdrawalServices.deleteWithdrawalById(withdrawalId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
