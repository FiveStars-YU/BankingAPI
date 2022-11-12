package com.FiveStarsYU.BankingAPI.repository;

import com.FiveStarsYU.BankingAPI.models.Bill;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillRepo extends CrudRepository<Bill, Long> {
    @Query(value = "SELECT * FROM bill WHERE account_id = ?1", nativeQuery = true)
    List<Bill> getAllBillsForASpecificAccount(Long account_id);

    @Query(value = "SELECT id FROM account WHERE customer_id = ?1", nativeQuery = true)
    List<Long> getAccountIdThatMatchesCustomerId(Long customer_id);

    @Query(value = "SELECT * FROM bill AS e WHERE e.account_id IN (:accountId)", nativeQuery = true)
    List<Bill> getAllBillsByAccountId(@Param("accountId") List<Long> accountId);
}
