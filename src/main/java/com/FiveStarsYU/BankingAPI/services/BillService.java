package com.FiveStarsYU.BankingAPI.services;

import com.FiveStarsYU.BankingAPI.models.Bill;
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

    public void verifyPoll(Long billId) {
        Bill bill = billRepo.findById(billId).orElse(null);
    }


    public List<Bill> getAllBillsForASpecificAccount(Long id) {
        return billRepo.getAllBillsForASpecificAccount(id);
    }
    public ResponseEntity<?> getBill(Long billId) {
        //verify bill id
        Optional<Bill> p = billRepo.findById(billId);
        return new ResponseEntity<> (p, HttpStatus.OK);
    }

    public List<Bill> getAllBillForOneCustomer(Long customerId) {
        //verify customer id
        List<Long> accountId = billRepo.getAccountIdThatMatchesCustomerId(customerId); //Getting the account that is assigned to a customer's id
        return billRepo.getAllBillsByAccountId(accountId);

    }

    public Bill createBill(Bill bill) {
        return billRepo.save(bill);
    }

    public void updateBill(Bill bill, Long billId) {
        //verify bill id
        billRepo.save(bill);
    }
    public void deleteBill(Long billId) {
        //verify bill id
        billRepo.deleteById(billId);
    }



}






