package com.example.itamarborges.popularmoviesstage2.pojo;

/**
 * Created by itamarborges on 18/02/18.
 */

public class MovieReview {
    String author;
    String content;

    public MovieReview(String author, String content) {
        this.author = author;
        this.content = content;
    }

    public String getAuthor() {

        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
