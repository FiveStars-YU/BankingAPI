package com.FiveStarsYU.BankingAPI.services;

import com.FiveStarsYU.BankingAPI.models.Account;
import com.FiveStarsYU.BankingAPI.models.Deposit;
import com.FiveStarsYU.BankingAPI.repository.AccountRepo;
import com.FiveStarsYU.BankingAPI.repository.DepositRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DepositService {
    @Autowired
    private DepositRepo depositRepo;

    @Autowired
    private AccountServices accountServices;

    @Autowired
    private AccountRepo accountRepo;

    public Deposit createDeposit(Long accountId,Deposit deposit){
       Optional<Account> account = accountServices.getAccountByAccountId(accountId);
        Double balance = account.get().getBalance();
        Double amount = deposit.getAmount();
        Double total = balance + amount;
        account.get().setBalance(total);
        return depositRepo.save(deposit);
    }
    public Iterable<Deposit> getAllDepositsByAccountId(Long accountId){
        return depositRepo.getAllDepositByAccountId(accountId);
    }
    public ResponseEntity<?> getDepositByDepositId(Long depositId){
        Deposit deposit = depositRepo.findById(depositId).orElse(null);
        return new ResponseEntity<>(deposit, HttpStatus.OK);
    }
    public void deleteDepositById(Long depositId){
        depositRepo.deleteById(depositId);
    }
    public void updateDeposit(Long depositId, Deposit deposit){
        Account account = accountServices.getAccountByAccountId(deposit.getPayeeId()).orElse(null);
        Double oldAmount = depositRepo.findById(depositId).get().getAmount();
        Double balance = account.getBalance();
        Double oldBalance = balance - oldAmount;
        account.setBalance(oldBalance);
        Double newAmount = deposit.getAmount();
        Double total = oldBalance + newAmount;
        account.setBalance(total);
        depositRepo.save(deposit);
    }
    public boolean depositCheck(Long accountId){
        Deposit deposit = depositRepo.findById(accountId).orElse(null);
        return deposit != null;
    }
    public boolean accountCheck(Long accountId){
        Account account = accountRepo.findById(accountId).orElse(null);
        return account != null;
    }
}
