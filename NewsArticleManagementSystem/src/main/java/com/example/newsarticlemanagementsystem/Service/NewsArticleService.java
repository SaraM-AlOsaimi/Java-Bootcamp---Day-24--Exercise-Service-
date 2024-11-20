package com.example.newsarticlemanagementsystem.Service;

import com.example.newsarticlemanagementsystem.Model.NewsArticle;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service
public class NewsArticleService {

    ArrayList<NewsArticle> newsArticles = new ArrayList<>();

// get all NewsArticles
    public ArrayList<NewsArticle> getNewsArticles(){
        return newsArticles;
    }

    // added new NewsArticle
    public void addNewsArticle(NewsArticle newsArticle){
        newsArticles.add(newsArticle);
    }

public boolean updateNewsArticle(String id , NewsArticle newsArticle){
    for (int i = 0; i < newsArticles.size(); i++) {
        if(newsArticles.get(i).getId().equals(id)){
            newsArticles.set(i,newsArticle);
            return true;
        }
    }
    return false;
}

public boolean deleteNewsArticle(String id){
    for (int i = 0; i < newsArticles.size(); i++) {
        if(newsArticles.get(i).getId().equals(id)){
            newsArticles.remove(i);
            return true;
        }
    }
    return false;
}

// Publish NewsArticles
public boolean publishNewsArticle(String id){
    for (int i = 0; i < newsArticles.size(); i++) {
        if(newsArticles.get(i).getId().equals(id) && !newsArticles.get(i).isPublished()){
            newsArticles.get(i).setPublished(true);
            return true;
        }
    }
    return false;
}


    public ArrayList<NewsArticle> getAllPublishedNewsArticles() {
        ArrayList<NewsArticle> publishedNewsArticles = new ArrayList<>();
        for (NewsArticle publishedNewsArticle : newsArticles) {
            if (publishedNewsArticle.isPublished()) {
                publishedNewsArticles.add(publishedNewsArticle);
            }
        }
        return publishedNewsArticles;
    }


    // Get NewsArticles by Category
    public ArrayList<NewsArticle> getNewsArticlesByCategory(String category){
        ArrayList<NewsArticle> newsArticlesByCategory = new ArrayList<>();
        for (NewsArticle newsArticle : newsArticles){
            if(newsArticle.getCategory().equals(category)){
                newsArticlesByCategory.add(newsArticle);
            }
        }
        return newsArticlesByCategory.isEmpty() ? null : newsArticlesByCategory;
    }

}
