package com.FiveStarsYU.BankingAPI.controllers;

import com.FiveStarsYU.BankingAPI.errorhandling.CodeData;
import com.FiveStarsYU.BankingAPI.errorhandling.CodeMessage;
import com.FiveStarsYU.BankingAPI.errorhandling.CodeMessageData;
import com.FiveStarsYU.BankingAPI.models.Bill;
import com.FiveStarsYU.BankingAPI.services.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BillController {
    @Autowired
    BillService billService;

    @PostMapping("/accounts/{accountId}/bills")
    public ResponseEntity<?> createBill(@RequestBody Bill bill, @PathVariable Long accountId) {
        try {
            if (!billService.accountCheck(accountId)) {
                CodeMessage exception = new CodeMessage("Error creating bill: Account not found");
                return new ResponseEntity<>(exception, HttpStatus.NOT_FOUND);
            } else {
                Bill b = billService.createBill(bill);
                CodeMessageData response = new CodeMessageData(201, "Created bill and added it to the account", b);
                return new ResponseEntity<>(response, HttpStatus.CREATED);
            } } catch (Exception e){
            CodeMessage error = new CodeMessage(404, "Error creating bill");
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }
    }

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

    @PutMapping("/bills/{billId}")
    public ResponseEntity<?> updateBill(@RequestBody Bill bill, @PathVariable Long billId){

        if(!billService.billCheck(billId)){
            CodeMessage exception = new CodeMessage("Bill ID does not exist");
            return new ResponseEntity<>(exception, HttpStatus.NOT_FOUND);
        }

        billService.updateBill(bill);
        CodeMessage response = new CodeMessage(202, "Accepted bill modification");
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }



    @DeleteMapping("/bills/{billId}")
    public ResponseEntity<?> deleteBill(@PathVariable Long billId){

        if(billId == null){
            CodeMessage exception = new CodeMessage("ERROR: A bill with this id does not exist");
            return new ResponseEntity<>(exception, HttpStatus.NOT_FOUND);
        }
        billService.deleteBill(billId);
        return new ResponseEntity<>("Bill with ID: " + billId + " has been deleted.", HttpStatus.OK);
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