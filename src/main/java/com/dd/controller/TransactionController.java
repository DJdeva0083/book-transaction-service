package com.dd.controller;

import com.dd.dto.BookDTO;
import com.dd.dto.TransactionDTO;
import com.dd.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/BookTransaction")
public class TransactionController {

@Autowired
TransactionService transactionService;

    @PostMapping("/addTransaction")
    public ResponseEntity<String> addNewTransaction(@RequestBody TransactionDTO TDTO) {
        String result = transactionService.borrowBook(TDTO);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PutMapping("/updateTransaction/{transactionId}")
    public ResponseEntity<String> UpdateTransaction(@PathVariable Long transactionId) {
        String result = transactionService.returnBook(transactionId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
