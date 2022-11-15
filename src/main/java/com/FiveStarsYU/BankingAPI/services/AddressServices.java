//package com.FiveStarsYU.BankingAPI.services;
//
//import com.FiveStarsYU.BankingAPI.models.Address;
//import com.FiveStarsYU.BankingAPI.repository.AddressRepo;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Service;
//
//@Service
//public class AddressServices {
//
//    @Autowired
//    AddressRepo addressRepo;
//
////    public void verifyAddress(Long addressId){
////        Address address= addressRepo.findById(addressId).orElse(null);
////    }
//    public void addAddress(Address address){
//        addressRepo.save(address);
//    }
//
//    public ResponseEntity<Iterable<Address>> getAllAddress(){
//        Iterable<Address> allAddress= addressRepo.findAll();
//        return new ResponseEntity<>(allAddress, HttpStatus.OK);
//    }
//
//    //should it be updated by customer instead of address id?
//    public ResponseEntity<?> updateAddress(Address address, Long id) {
//        Address a = addressRepo.save(address);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
//
//    public void deleteAddressById(Long addressId) {
//
//        addressRepo.deleteById(addressId);
//    }
//
//}
