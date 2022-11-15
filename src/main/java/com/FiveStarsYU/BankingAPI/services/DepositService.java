//package com.FiveStarsYU.BankingAPI.services;
//
//import com.FiveStarsYU.BankingAPI.models.Account;
//import com.FiveStarsYU.BankingAPI.models.Deposit;
//import com.FiveStarsYU.BankingAPI.repository.AccountRepo;
//import com.FiveStarsYU.BankingAPI.repository.DepositRepo;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Service;
//
//import java.util.Optional;
//
//@Service
//public class DepositService {
//    @Autowired
//    private DepositRepo depositRepo;
//
//  //  @Autowired
//   // private AccountServices accountServices;
//
//    @Autowired
//    private AccountRepo accountRepo;
//
//   // public Deposit createDeposit(Deposit deposit, Long accountId){
//    //    accountServices.getAccountByAccountId()
//   // }
//    public Iterable<Deposit> getAllDepositsByAccountId(Long accountId){
//        return depositRepo.getAllDepositByAccountId(accountId);
//    }
//    public ResponseEntity<?> getDepositByDepositId(Long depositId){
//        Deposit deposit = depositRepo.findById(depositId).orElse(null);
//        return new ResponseEntity<>(deposit, HttpStatus.OK);
//    }
//    public void deleteDepositById(Long depositId){
//        depositRepo.deleteById(depositId);
//    }
//    public void updateDeposit(Long depositId, Deposit deposit){
//        depositRepo.findById(depositId).map(account ->{
//
//       })
//    }
//}
