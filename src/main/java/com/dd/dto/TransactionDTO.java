package com.dd.dto;


import com.dd.entity.Book;
import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionDTO {


    private Long userId;

    private Long bookId;

    private LocalDate borrowDate;

    private LocalDate returnDate;

    private boolean returned;
}
