package com.FiveStarsYU.BankingAPI.repository;

import com.FiveStarsYU.BankingAPI.models.Deposit;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepositRepo extends CrudRepository<Deposit, Long> {
    @Query(value = "SELECT * FROM deposit WHERE payee_id = ?1", nativeQuery = true)
    Iterable<Deposit> getAllDepositByAccountId(long accountId);
}
