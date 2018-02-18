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
    private Double runtime;
    private String tagline;


    public MovieDetails(String title, String releaseDate, Double voteAverage, String plot, String urlCover, Double runtime, String tagline) {
        this.title = title;
        this.releaseDate = releaseDate;
        this.voteAverage = voteAverage;
        this.plot = plot;
        this.urlCover = urlCover;
        this.runtime = runtime;
        this.tagline = tagline;
    }

    public Double getRuntime() {
        return runtime;
    }

    public void setRuntime(Double runtime) {
        this.runtime = runtime;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
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
