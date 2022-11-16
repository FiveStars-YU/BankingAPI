package com.FiveStarsYU.BankingAPI.repository;

import com.FiveStarsYU.BankingAPI.models.Bill;
import com.FiveStarsYU.BankingAPI.models.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepo extends CrudRepository<Customer,Long> {


}
