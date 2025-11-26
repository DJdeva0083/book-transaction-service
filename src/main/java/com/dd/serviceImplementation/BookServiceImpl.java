package com.dd.serviceImplementation;

import java.util.*;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.dd.dto.BookDTO;
import com.dd.entity.Book;
import com.dd.exception.BookException;
import com.dd.mapper.BookMapper;
import com.dd.repositories.BookRepository;
import com.dd.service.BookService;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    BookRepository bookRepository;
    @Autowired
    BookMapper bookMapper; //

    @Override
    public String addBook(List<BookDTO> bookDtos) throws BookException {
        if (bookDtos == null || bookDtos.isEmpty()) {
            throw new BookException("Book list is empty", HttpStatus.NOT_FOUND);
        }
        List<Book> books = bookDtos.stream()
                .filter(Objects::nonNull)
                .map(bookDTO -> bookMapper.toEntity(bookDTO))
                .toList();
        bookRepository.saveAll(books);

        return "Book added successfully";
    }

    @Override
    public List<BookDTO> getAllBooks() {
        return bookRepository.findAll().stream()
                .map(bookMapper::toDTO)

                .collect(Collectors.toList());


    }


    @Override
    public String removeBook(Long id) {
        Optional.ofNullable(id)
                .orElseThrow(() -> new BookException("Book ID cannot be null", null));
        Book book = bookRepository.findById(id).
                orElseThrow(() -> new BookException("Book not found with ID: " + id, null));
        bookRepository.delete(book);
        return "Book removed successfully with ID: " + id;

    }


    @Override
    public BookDTO getBook(Long id) {
        Book books = bookRepository.findById(id)
                .orElseThrow(() -> new BookException("No record found", null));
        BookDTO dto = bookMapper.toDTO(books);
        return dto;

    }


    public void updateBook(BookDTO bookDto, Long Id) {
        // TODO Auto-generated method stub
        Book existingBook = bookRepository.findById(Id)
                .orElseThrow(() -> new BookException("No book to update", null));
        bookMapper.updateBookFromDto(bookDto, existingBook);// Only update the provided vales and others are ignored

    }


    // Implement methods for book service here
    // For example, methods to add, update, delete, and retrieve books

}
