package com.FiveStarsYU.BankingAPI.services;

import com.FiveStarsYU.BankingAPI.models.Account;
import com.FiveStarsYU.BankingAPI.models.Bill;
import com.FiveStarsYU.BankingAPI.repository.AccountRepo;
import com.FiveStarsYU.BankingAPI.repository.BillRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BillService {

    @Autowired
    private BillRepo billRepo;
    @Autowired
    AccountRepo accountRepo;




    public List<Bill> getAllBillsForASpecificAccount(Long id) {
        return billRepo.getAllBillsForASpecificAccount(id);
    }
    public ResponseEntity<?> getBill(Long billId) {
        Optional<Bill> p = billRepo.findById(billId);
        return new ResponseEntity<> (p, HttpStatus.OK);
    }

    public List<Bill> getAllBillForOneCustomer(Long customerId) {
        List<Long> accountId = billRepo.getAccountIdThatMatchesCustomerId(customerId); //Getting the account that is assigned to a customer's id
        return billRepo.getAllBillsByAccountId(accountId);

    }

    public Bill createBill(Bill bill) {
        return billRepo.save(bill);
    }

    public void updateBill(Bill bill) {
        billRepo.save(bill);
    }
    public void deleteBill(Long billId) {
        billRepo.deleteById(billId);
    }

    public boolean accountCheck(Long accountId){

        Account account = accountRepo.findById(accountId).orElse(null);
        return account != null;
    }

    public Bill billCheck(Long billId){

        Bill bill = billRepo.findById(billId).orElse(null);
        return bill;
    }

}






