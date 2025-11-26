package com.dd.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dd.entity.Book;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    // Additional query methods can be defined here if needed
    List<Book> findByAvailableTrue();

}
