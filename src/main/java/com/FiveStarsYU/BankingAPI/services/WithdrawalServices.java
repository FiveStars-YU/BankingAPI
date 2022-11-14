package com.FiveStarsYU.BankingAPI.services;

import com.FiveStarsYU.BankingAPI.models.Account;
import com.FiveStarsYU.BankingAPI.models.Withdrawal;
import com.FiveStarsYU.BankingAPI.repository.AccountRepo;
import com.FiveStarsYU.BankingAPI.repository.WithdrawalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WithdrawalServices {
    @Autowired
    private WithdrawalRepository withdrawalRepository;
    @Autowired
    private AccountRepo accountRepo;
    @Autowired
    private AccountServices accountServices;

    public Withdrawal createWithdrawal(Long accountId,Withdrawal withdrawal){
        Optional<Account> account = accountServices.getAccountByAccountId(accountId);
        Double accountBalance = account.get().getBalance();
        Double withdrawalAmount = withdrawal.getAmount();
        Double total = accountBalance - withdrawalAmount;
        account.get().setBalance(total);
        return withdrawalRepository.save(withdrawal);
    }

    public Optional<Withdrawal> getWithdrawalById(Long withdrawalId){
        return withdrawalRepository.findById(withdrawalId);
    }

    public Iterable<Withdrawal> getAllWithdrawalsByAccountId(Long accountId){
        return withdrawalRepository.getAllWithdrawalsByAccountId(accountId);
    }


    public void updateWithdrawal(Long withdrawalId, Withdrawal withdrawal) {
        Account account = accountServices.getAccountByAccountId(withdrawal.getPayerId()).orElse(null);
        Double oldWithdrawal = withdrawalRepository.findById(withdrawalId).get().getAmount();
        Double balance = account.getBalance();
        Double oldBalance = balance + oldWithdrawal;
        account.setBalance(oldBalance);

        Double withdrawalAmount = withdrawal.getAmount();
        account.setBalance(oldBalance - withdrawalAmount);
        withdrawalRepository.save(withdrawal);
    }

    public boolean accountCheck(Long accountId){
        Account account = accountRepo.findById(accountId).orElse(null);
        return account != null;
    }

    public boolean withdrawalCheck(Long withdrawalId){
        Withdrawal withdrawal = withdrawalRepository.findById(withdrawalId).orElse(null);
        return withdrawal != null;
    }

    public void deleteWithdrawalById(Long withdrawalId){
        withdrawalRepository.deleteById(withdrawalId);
    }
}
