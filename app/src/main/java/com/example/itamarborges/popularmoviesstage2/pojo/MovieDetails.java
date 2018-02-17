package com.example.itamarborges.popularmoviesstage2.pojo;

/**
 * Created by itamarborges on 29/12/17.
 */

public class MovieDetails {
    private String title;
    private String releaseDate;
    private Double voteAverage;
    private String plot;
    private String urlCover;


    public MovieDetails(String title, String releaseDate, Double voteAverage, String plot, String urlCover) {
        this.title = title;
        this.releaseDate = releaseDate;
        this.voteAverage = voteAverage;
        this.plot = plot;
        this.urlCover = urlCover;
    }

    public String getTitle() {

        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(Double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public String getUrlCover() {
        return urlCover;
    }

    public void setUrlCover(String urlCover) {
        this.urlCover = urlCover;
    }
}
