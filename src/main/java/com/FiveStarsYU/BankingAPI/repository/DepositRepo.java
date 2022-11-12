package com.FiveStarsYU.BankingAPI.repository;

import com.FiveStarsYU.BankingAPI.models.Deposit;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepositRepo extends CrudRepository<Deposit, Long> {
    Iterable<Deposit> getAllDepositByAccountId(long accountId);
}
