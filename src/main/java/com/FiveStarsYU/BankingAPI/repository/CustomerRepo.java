package com.FiveStarsYU.BankingAPI.repository;

import com.FiveStarsYU.BankingAPI.models.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepo extends CrudRepository<Customer,Long> {

    Iterable<Customer> findByAccountId(Long accountId);
}
