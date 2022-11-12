package com.FiveStarsYU.BankingAPI.services;

import com.FiveStarsYU.BankingAPI.models.Account;
import com.FiveStarsYU.BankingAPI.models.Customer;
import com.FiveStarsYU.BankingAPI.repository.AccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountServices {

    @Autowired
    private AccountRepo accountRepo;

    @Autowired
    private CustomerRepo customerRepo;

    //check customer
    public boolean customerCheck(Long accountId){

        Customer customer = customerRepo.findById(accountId).orElse(null);
        return customer != null;
    }

    public boolean accountCheck(Long accountId){

        Account account = getAccountByAccountId(accountId).orElse(null);
        return account != null;
    }

    //get all accounts
    public Iterable<Account> getAllAccounts(){
        return accountRepo.findAll();
    }

    //get all accounts by account id
    public Optional<Account> getAccountByAccountId(Long accountId){
        return accountRepo.findById(accountId);
    }

    //get all accounts by customer id
    public Iterable<Account> getAllAccountsByCustomerId(Long customerId){
        return accountRepo.getAllAccountsByCustomerId(customerId);
    }

    //create an account
    public Account createAccount(Account account){
        return accountRepo.save(account);
    }

    //update account by account id

    public void updateAccountByAccountId(Long accountId, Account account){
        verifyAccount(accountId);
        accountRepo.save(account);
    }

    //delete account by account id
    public void deleteAccountbByAccountId(Long accountId){
        accountRepo.deleteById(accountId);
    }

}
