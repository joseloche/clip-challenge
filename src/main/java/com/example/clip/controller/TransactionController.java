package com.example.clip.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.clip.request.PaymentRequest;
import com.example.clip.service.TransactionService;

@RestController
@RequestMapping("/api/clip")
@Validated
public class TransactionController {


    @Autowired
    private TransactionService transactionService;

    @PostMapping(value = "/createPayload")
    public ResponseEntity<?> create(@Valid @RequestBody PaymentRequest paymentRequest){
    	return ResponseEntity.ok(transactionService.create(paymentRequest));
    }


    @GetMapping(value="/users/payment")
    public ResponseEntity<?> getUserWithPayment(){
    	return ResponseEntity.ok(transactionService.getUsers());
    }
    
    @PostMapping(value="/disbursement")
    public ResponseEntity<?> processDisbursement(){
    	return ResponseEntity.ok(transactionService.disbursementProcess());
    }
    
    @GetMapping(value="/report/{id}")
    public ResponseEntity<?> getReport(@PathVariable Long id){
    	return ResponseEntity.ok(transactionService.getReportByIdUser(id));
    }

}
