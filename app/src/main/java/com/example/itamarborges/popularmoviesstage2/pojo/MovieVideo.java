package com.example.itamarborges.popularmoviesstage2.pojo;

/**
 * Created by itamarborges on 18/02/18.
 */

public class MovieVideo {
    String key;
    String name;

    public MovieVideo(String key, String name) {
        this.key = key;
        this.name = name;
    }

    public String getKey() {

        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
