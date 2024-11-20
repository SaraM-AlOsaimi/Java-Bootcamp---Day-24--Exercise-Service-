package com.example.newsarticlemanagementsystem.Controller;

import com.example.newsarticlemanagementsystem.ApiResponse.ApiResponse;
import com.example.newsarticlemanagementsystem.Model.NewsArticle;
import com.example.newsarticlemanagementsystem.Service.NewsArticleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;


@RestController
@RequestMapping("/api/v1/news/article")
@RequiredArgsConstructor
public class NewsArticleController {

  private final NewsArticleService newsArticleService;

//CRUD
    // Get
    @GetMapping("/get")
public ResponseEntity<?> getNewsArticles(){
    return ResponseEntity.status(200).body(newsArticleService.getNewsArticles());
}
//Post
    @PostMapping("/add")
    public ResponseEntity<?> addNewsArticle(@RequestBody @Valid NewsArticle newsArticle , Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        newsArticleService.addNewsArticle(newsArticle);
        return ResponseEntity.status(200).body(new ApiResponse("News Article addedSuccessfully"));
    }

    // Update
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateNewsArticle(@PathVariable String id , @RequestBody @Valid NewsArticle newsArticle , Errors errors){
       if(errors.hasErrors()){
           return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
       }
        boolean isUpdate = newsArticleService.updateNewsArticle(id,newsArticle);
        if(isUpdate){
            return ResponseEntity.status(200).body(new ApiResponse("News Article Updated successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("News Article ID not found!"));
    }

    // delete
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteNewsArticle(@PathVariable String id){
       boolean isFound = newsArticleService.deleteNewsArticle(id);
       if(isFound){
           return ResponseEntity.status(200).body(new ApiResponse("News Article deleted successfully"));
       }
       return ResponseEntity.status(400).body(new ApiResponse("News Article ID not found!"));
    }

    //Publish NewsArticles.
    @PutMapping("/publish/{id}")
    public ResponseEntity<?> publishNewsArticle(@PathVariable String id) {
        boolean isPublished = newsArticleService.publishNewsArticle(id);
        if (isPublished) {
            return ResponseEntity.status(200).body(new ApiResponse("News Article Published"));
        } else {
            return ResponseEntity.status(400).body(new ApiResponse("News Article already published or ID not found"));
        }
    }

    //Get all Published NewsArticles
    @GetMapping("/get/published/news")
    public ResponseEntity<?> getAllPublishedNewsArticles() {
        ArrayList<NewsArticle> publishedNewsArticles = newsArticleService.getAllPublishedNewsArticles();

        if (publishedNewsArticles.isEmpty()) {
            return ResponseEntity.status(404).body("No published news articles found.");
        } else {
            return ResponseEntity.status(200).body(publishedNewsArticles);
        }
    }

    //Get NewsArticles by Category.
    @GetMapping("/get/news/articles/by/category/{category}")
    public ResponseEntity<?> getNewsArticlesByCategory(@PathVariable String category){
        ArrayList<NewsArticle> articlesByCategory = newsArticleService.getNewsArticlesByCategory(category);
        if(articlesByCategory == null || articlesByCategory.isEmpty()){
            return ResponseEntity.status(400).body(new ApiResponse("No News Articles found with that category"));
        }
        return ResponseEntity.status(200).body(articlesByCategory);
    }


}
