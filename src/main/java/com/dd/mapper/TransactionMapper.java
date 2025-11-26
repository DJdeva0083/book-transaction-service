package com.dd.mapper;

import com.dd.dto.TransactionDTO;
import com.dd.entity.Book;
import com.dd.entity.Transaction;
import org.mapstruct.Mapper;

import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TransactionMapper {

    TransactionDTO toDto(Transaction transaction);


    @Mapping(target = "book", source = "bookId", qualifiedByName = "mapBook")
    Transaction toEntity(TransactionDTO transactionDTO);
    @org.mapstruct.Named("mapBook")
    default Book mapBook(Long bookId) {
        if (bookId == null) return null;
        Book book = new Book();
        book.setBookId(bookId);
        return book;
    }

}
