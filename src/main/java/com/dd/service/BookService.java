package com.dd.service;

import java.net.http.HttpResponse;
import java.util.List;
import java.util.Optional;

import com.dd.dto.BookDTO;
import com.dd.entity.Book;
import com.dd.exception.BookException;

public interface BookService {
    public String addBook(List<BookDTO> bookDto);


    public List<BookDTO> getAllBooks();

    public BookDTO getBook(Long id);


    public String removeBook(Long id);


    public void updateBook(BookDTO bookDto, Long Id);

}
