package com.example.itamarborges.popularmoviesstage2.pojo;

/**
 * Created by itamarborges on 29/12/17.
 */

public class MovieCover {
    private int id;
    private String urlCover;
    private String title;

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrlCover() {
        return urlCover;
    }

    public void setUrlCover(String urlCover) {
        this.urlCover = urlCover;
    }

    public MovieCover(int id, String urlCover, String title) {
        this.id = id;
        this.urlCover = urlCover;
        this.title = title;
    }
}
