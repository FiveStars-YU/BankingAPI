package com.FiveStarsYU.BankingAPI.repository;

import com.FiveStarsYU.BankingAPI.models.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepo extends CrudRepository<Account,Long> {
    Iterable<Account> getAllAccountsByCustomerId(Long customerId);
}
