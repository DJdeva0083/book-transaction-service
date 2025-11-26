package com.dd.serviceImplementation;

import com.dd.dto.TransactionDTO;
import com.dd.entity.Book;
import com.dd.entity.Transaction;
import com.dd.exception.BookException;
import com.dd.exception.TransactionException;
import com.dd.mapper.TransactionMapper;
import com.dd.repositories.BookRepository;
import com.dd.repositories.TransactionRepository;
import com.dd.service.TransactionService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

@Service
@Transactional
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    BookRepository bookRepository;
    @Autowired
    TransactionMapper transactionMapper;


    @Override
    public String borrowBook(TransactionDTO TDTO) {
        Book book = bookRepository.findById(TDTO.getBookId())
                .orElseThrow(() -> new BookException("The entered book is not available", null));
        if (book.isAvailable()) {
            Transaction transaction = new Transaction();
            transaction.setBorrowDate(LocalDate.now());
            transaction.setReturnDate(TDTO.getReturnDate());
            transaction.setBook(book);
            transaction.setUserId(TDTO.getUserId());
            transaction.setReturned(false);
            book.setAvailable(false);
            bookRepository.save(book);
            transactionRepository.save(transaction);
            return "Transaction entry for the book with id: " + book.getBookId() + "has been added";

        } else
            throw new BookException("Book is not available at the moment", null);
    }

    @Override
    public String returnBook(Long transactionId) {
        Transaction transaction = transactionRepository.findById(transactionId)
                .orElseThrow(() -> new TransactionException("Transaction Id invalid", null));

        if (!transaction.isReturned()) {
            transaction.setReturnDate(LocalDate.now());
            transaction.setReturned(true);
            transaction.getBook().setAvailable(true);
            transactionRepository.save(transaction);
            bookRepository.save(transaction.getBook());
            return "Thank you for the return";

        } else throw new TransactionException("The return is already done", null);
    }
}
