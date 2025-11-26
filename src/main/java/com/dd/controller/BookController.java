package com.dd.controller;

import org.springframework.web.bind.annotation.*;

import com.dd.dto.BookDTO;
import com.dd.service.BookService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/v1/book")
public class BookController {
    @Autowired
    BookService bookService;

    @PostMapping("/add")
    public ResponseEntity<String> addNewBook(@RequestBody List<BookDTO> bookDtos) {
        String result = bookService.addBook(bookDtos);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @GetMapping("/getbooks/{id}")
    public ResponseEntity<BookDTO> getBooks(@PathVariable(required = true) Long id) {

        BookDTO bookDTOS = bookService.getBook(id);
        return new ResponseEntity<>(bookDTOS, HttpStatus.ACCEPTED);
    }

    @GetMapping("/getbooks/all")
    public ResponseEntity<List<BookDTO>> getBooks() {

        List<BookDTO> bookDTOS = bookService.getAllBooks();
        return new ResponseEntity<>(bookDTOS, HttpStatus.ACCEPTED);
    }


}
