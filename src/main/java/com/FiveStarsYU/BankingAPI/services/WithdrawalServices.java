package com.FiveStarsYU.BankingAPI.services;

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
    AccountRepo accountRepo;

    public void verifyWithdrawal(Long withdrawalId){
        Withdrawal withdrawal = withdrawalRepository.findById(withdrawalId).orElse(null);
    }

    public Withdrawal createWithdrawal(Withdrawal withdrawal){
       return withdrawalRepository.save(withdrawal);
    }

    public Optional<Withdrawal> getWithdrawalById(Long withdrawalId){
        return withdrawalRepository.findById(withdrawalId);
    }

    public Iterable<Withdrawal> getAllWithdrawalsByAccountId(Long accountId){
        return withdrawalRepository.getAllWithdrawalsByAccountId(accountId);
    }


    public void updateWithdrawal(Long withdrawalId, Withdrawal withdrawal) {
        verifyWithdrawal(withdrawalId);
        withdrawalRepository.save(withdrawal);
    }

    public void deleteWithdrawal(Long withdrawalId){
        withdrawalRepository.deleteById(withdrawalId);
    }
}
