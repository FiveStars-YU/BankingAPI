<<<<<<< HEAD
//package com.FiveStarsYU.BankingAPI.controllers;
//
//import com.FiveStarsYU.BankingAPI.models.Customer;
//import com.FiveStarsYU.BankingAPI.models.Deposit;
//import com.FiveStarsYU.BankingAPI.repository.DepositRepo;
//import com.FiveStarsYU.BankingAPI.services.DepositService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//public class DepositController {
//
//    @Autowired
//    private DepositRepo depositRepo;
//    @Autowired
//    private DepositService depositService;
//
//   // @PostMapping("/deposit/{customerId}/deposit")
//   // public void createDeposit(@PathVariable Long customerId, @RequestBody Customer customer){
//     //   depositService.createDeposit(accountId,deposit);
//  //  }
//    @GetMapping("/deposit/{accountId}/deposit")
//    public Iterable<Deposit> getAllDepositsByAccountId(@PathVariable Long accountId){
//        return depositService.getAllDepositsByAccountId(accountId);
//    }
//    @GetMapping("/deposit/{depositId}")
//    public ResponseEntity<?> getDepositById(@PathVariable Long depositId){
//        return depositService.getDepositByDepositId(depositId);
//    }
//    @DeleteMapping("/deposit/{depositId}")
//    public void deleteDepositById(@PathVariable Long depositId){
//        depositService.deleteDepositById(depositId);
//    }
//}
=======
package com.FiveStarsYU.BankingAPI.controllers;

import com.FiveStarsYU.BankingAPI.errorhandling.CodeData;
import com.FiveStarsYU.BankingAPI.errorhandling.CodeMessage;
import com.FiveStarsYU.BankingAPI.errorhandling.CodeMessageData;
import com.FiveStarsYU.BankingAPI.models.Deposit;
import com.FiveStarsYU.BankingAPI.repository.DepositRepo;
import com.FiveStarsYU.BankingAPI.services.AccountServices;
import com.FiveStarsYU.BankingAPI.services.DepositService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class DepositController {

    @Autowired
    private DepositRepo depositRepo;
    @Autowired
    private DepositService depositService;
    @Autowired
    private AccountServices accountServices;

    @PostMapping("/deposit/{customerId}/deposit")
    public ResponseEntity<?> createDeposit(@PathVariable Long accountId, @RequestBody Deposit deposit){
       depositService.createDeposit(accountId,deposit);
        if(!accountServices.accountCheck(accountId)){
            CodeMessage noAccount = new CodeMessage(404,"Account doesn't exist");
            return new ResponseEntity<>(noAccount,HttpStatus.NOT_FOUND);
        }
        else if(deposit.getAmount() <= 0){
            CodeMessage createError = new CodeMessage("error creating deposit: deposit must be greater than 0");
            return new ResponseEntity<>(createError,HttpStatus.BAD_REQUEST);
        }else{
            Deposit successfulDeposit = depositService.createDeposit(accountId,deposit);
            CodeMessageData successDepo = new CodeMessageData(201,"Created the deposit and added it to your account", successfulDeposit);
            return new ResponseEntity<>(successDepo,HttpStatus.CREATED);
        }
    }
    @GetMapping("/deposit/{accountId}/deposit")
    public ResponseEntity<?> getAllDepositsByAccountId(@PathVariable Long accountId){
        Iterable<Deposit> deposits = depositService.getAllDepositsByAccountId(accountId);
        if(deposits.iterator().hasNext()){
            CodeData response = new CodeData(200, deposits);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        if(!accountServices.accountCheck(accountId)){
            CodeMessage message = new CodeMessage(404,"Account doesn't exist");
            return new ResponseEntity<>(message,HttpStatus.NOT_FOUND);
        }
        CodeMessage errorReturn = new CodeMessage(404,"Deposit not found broke boy");
        return new ResponseEntity<>(errorReturn,HttpStatus.NOT_FOUND);
    }
    @GetMapping("/deposit/{depositId}")
    public ResponseEntity<?> getDepositById(@PathVariable Long depositId){
        ResponseEntity<?> deposit = depositService.getDepositByDepositId(depositId);
        if(!depositService.depositCheck(depositId)){
            CodeMessage getDepositByIdError = new CodeMessage(404, "Could not find deposit Id");
            return new ResponseEntity<>(getDepositByIdError,HttpStatus.NOT_FOUND);
        }else{
            CodeData successfullyFoundDepositById = new CodeData(200,deposit);
            return new ResponseEntity<>(successfullyFoundDepositById,HttpStatus.OK);
        }

    }
    @DeleteMapping("/deposit/{depositId}")
    public ResponseEntity<?> deleteDepositById(@PathVariable Long depositId){
        if (!depositService.depositCheck(depositId)){
            CodeMessage deleteError = new CodeMessage(404,"This id does not exist");
            return new ResponseEntity<>(deleteError,HttpStatus.NOT_FOUND);
        }else {
            depositService.deleteDepositById(depositId);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }
    @PutMapping("/deposit/{accountId}/deposit")
    public ResponseEntity<?> updateDeposit(@PathVariable Long depositId, @RequestBody Deposit deposit){
        if (!depositService.depositCheck(depositId)){
            CodeMessage updateError = new CodeMessage(404,"Deposit does not exist");
            return new ResponseEntity<>(updateError,HttpStatus.NOT_FOUND);
        }else{
            depositService.updateDeposit(depositId, deposit);
            CodeMessage successfullyUpdated = new CodeMessage(202,"Successfully update your deposit");
            return new ResponseEntity<>(successfullyUpdated,HttpStatus.OK);
        }
    }
}
>>>>>>> d31567201c840823ad8a25c610fe28c0b62c16b8
