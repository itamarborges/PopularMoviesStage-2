package com.example.itamarborges.popularmoviesstage2.pojo;

/**
 * Created by itamarborges on 29/12/17.
 */

public class MovieCover {
    private int id;
    private String urlCover;

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

    public MovieCover(int id, String urlCover) {
        this.id = id;
        this.urlCover = urlCover;
    }
}
