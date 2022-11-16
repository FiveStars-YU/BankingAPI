package com.FiveStarsYU.BankingAPI.repository;

import com.FiveStarsYU.BankingAPI.models.Withdrawal;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface WithdrawalRepository extends CrudRepository<Withdrawal, Long> {
    @Query(value = "SELECT * FROM withdrawal WHERE payer_id = ?1", nativeQuery = true)
    Iterable<Withdrawal> getAllWithdrawalsByAccountId(Long accountId);
}
