package com.dd.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookDTO {

    private Long id;


    @NotBlank
    @NotNull
    private String title;

    private String author;
    @NotBlank
    @NotNull
    @Column(unique = true)
    private String isbn;

    private boolean available = true;
}
