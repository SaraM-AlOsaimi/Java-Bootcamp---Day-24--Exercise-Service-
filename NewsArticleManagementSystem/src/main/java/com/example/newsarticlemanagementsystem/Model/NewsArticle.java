package com.example.newsarticlemanagementsystem.Model;


import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Data
@AllArgsConstructor
public class NewsArticle {

    // NewsArticle Model has:
    //▪ id:
    //- Cannot be null.
    @NotEmpty(message = "ID is Empty")
    private String id;

    // ▪ title:
    //- Cannot be null.
    //- Maximum length of 100 characters.
    @NotEmpty(message = "Title is Empty")
    @Size(max = 100 , message = "Title maximum is 100 characters")
    private String title;

    //▪ author:
    //- Cannot be null.
    //- Must be more than 4 characters.
    //- Maximum length of 20 characters.
    @NotEmpty(message = "Author is Empty")
    @Size(min = 5 , max = 20 , message = "Author filed should be the range form 5 to 20 characters")
    private String author;

    //▪ content:
    //- Cannot be null.
    //- Must be more than 200 characters.
    @NotEmpty(message = "Content is Empty")
    @Size(min = 201, message = "Must be more than 200 characters")
    private String content;

    //▪ category:
    //- Cannot be null.
    //- Must be either "politics", " sports" or " technology" only.
    @NotEmpty(message = "Category is Empty")
    @Pattern(regexp = "^politics|sports|technology$" , message = "Category must be either : politics, sports or technology only ")
    private String category;

    //▪ imageUrl:
    //- Cannot be null.
    @NotEmpty(message = "Image url is Empty")
//    @Pattern(regexp = "^(http(s?)://)([^/]+/)*([^/]+\\.(jpg|png|gif))$")
    private String imageUrl;

    //▪ isPublished
    //- Must be by default false.
    @AssertFalse(message = "Must be false")
    private boolean isPublished;


    //▪ publishDate
    @NotEmpty(message = "Publish Date is Empty")
    private LocalDate publishDate;

}
