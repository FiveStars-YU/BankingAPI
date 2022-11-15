//package com.FiveStarsYU.BankingAPI.controllers;
//
//import com.FiveStarsYU.BankingAPI.models.Address;
//import com.FiveStarsYU.BankingAPI.services.AddressServices;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//public class AddressController {
//
//    @Autowired
//    private AddressServices addressServices;
//
//    @PostMapping("/address")
//    public void addAddress(@RequestBody Address address){
//        addressServices.addAddress(address);
//    }
//
//    @GetMapping("/addresses")
//    public ResponseEntity<?> getAllAddresses(){
//
//        return addressServices.getAllAddress();
//    }
//
//    //should it be by customerId instead
//    @PutMapping("/address/{id}")
//    public ResponseEntity<?> updateAddress(@RequestBody Address address, @PathVariable Long id) {
//
//        return addressServices.updateAddress(address,id);
//    }
//
//    @DeleteMapping("/address/{addressId}")
//    public void deleteAddressById(@PathVariable Long addressId){
//
//        addressServices.deleteAddressById(addressId);
//    }
//
//
//}
