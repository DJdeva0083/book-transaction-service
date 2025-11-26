package com.dd.service;

import com.dd.dto.TransactionDTO;
import com.dd.entity.Book;

import java.util.Date;

public interface TransactionService {

    public String borrowBook(TransactionDTO TDTO);

    public String returnBook(Long bookid);


}
