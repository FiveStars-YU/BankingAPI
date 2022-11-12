package com.FiveStarsYU.BankingAPI.controllers;

import com.FiveStarsYU.BankingAPI.errorhandling.CodeData;
import com.FiveStarsYU.BankingAPI.errorhandling.CodeMessage;
import com.FiveStarsYU.BankingAPI.models.Bill;
import com.FiveStarsYU.BankingAPI.services.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BillController {
    @Autowired
    BillService billService;

    @GetMapping("/accounts/{accountId}/bills")
    public ResponseEntity<?> getAllBillsByAccount(@PathVariable Long accountId) {

        List<Bill> bills = billService.getAllBillsForASpecificAccount(accountId);
        if (bills.isEmpty()) {
            CodeMessage exception = new CodeMessage("ERROR: No bills under account id: " + accountId);
            return new ResponseEntity<>(exception, HttpStatus.NOT_FOUND);
        }
        CodeData response = new CodeData(200, bills);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/bills/{billId}")
    public ResponseEntity<?> getBillById(@PathVariable Long billId) {

        ResponseEntity<?> bill = billService.getBill(billId);
        if (bill == null) {
            CodeMessage exception = new CodeMessage("ERROR: No bill found with the specified id: " + billId);
            return new ResponseEntity<>(exception, HttpStatus.NOT_FOUND);
        }

        CodeData response = new CodeData(200, bill);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/customers/{customerId}/bills")
    public ResponseEntity<?> getCustomerBillsByCustomerId(@PathVariable Long customerId){
        List<Bill> bills = billService.getAllBillForOneCustomer(customerId);
        if(bills.isEmpty()){
            CodeMessage exception = new CodeMessage("ERROR: A bill with this id does not exist");
            return new ResponseEntity<>(exception, HttpStatus.NOT_FOUND);
        }

        CodeData response = new CodeData(200, bills);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }



    @DeleteMapping("/bills/{billId}")
    public ResponseEntity<?> deleteBill(@PathVariable Long billId){

        if(billId == null){
            CodeMessage exception = new CodeMessage("ERROR: A bill with this id does not exist");
            return new ResponseEntity<>(exception, HttpStatus.NOT_FOUND);
        }
        billService.deleteBill(billId);
        return null;
    }
}





/*
Method: GET URI: /accounts/{accountId}/bills Action: Get all bills for a specific
account
Method: GET URI: /bills/{billId} Action: Get bill by id
Method: GET URI: /customers/{customerId}/bills Action: Get all bills for customer
Method: POST /accounts/{accountId}/bills Create a bill
Method: PUT /bills/{billId} Update a specific existing bill
Method:DELETE /bills/{billId} Delete a specific existing bill
*/