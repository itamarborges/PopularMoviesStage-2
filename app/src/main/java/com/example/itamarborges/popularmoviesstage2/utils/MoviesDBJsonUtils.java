package com.example.itamarborges.popularmoviesstage2.utils;

import com.example.itamarborges.popularmoviesstage2.pojo.MovieDetails;
import com.example.itamarborges.popularmoviesstage2.pojo.MovieCover;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by itamarborges on 29/12/17.
 */

public final class MoviesDBJsonUtils {

    public static List<MovieCover> getMoviesCoverFromJson(String moviesJsonStr)
            throws JSONException {

        final String MOVIES_RESULTS = "results";

        final String MOVIE_ID = "id";
        final String MOVIES_POSTER_PATH = "poster_path";

        final String urlBaseCover = "http://image.tmdb.org/t/p/w185";

        List<MovieCover> mMoviesCover = new ArrayList<MovieCover>();

        JSONObject moviesJson = new JSONObject(moviesJsonStr);

        JSONArray moviesArray = moviesJson.getJSONArray(MOVIES_RESULTS);

        for (int i = 0; i < moviesArray.length(); i++) {
            int idMovie;
            String pathMovieCover;

            JSONObject movieItem = moviesArray.getJSONObject(i);
            idMovie = movieItem.getInt(MOVIE_ID);
            pathMovieCover = urlBaseCover.concat(movieItem.getString(MOVIES_POSTER_PATH));

            MovieCover movieCover = new MovieCover(idMovie, pathMovieCover);

            mMoviesCover.add(movieCover);

        }
        return mMoviesCover;
    }

    public static MovieDetails getMovieDetails(String movieJsonStr)
            throws JSONException {

        final String MOVIE_TITLE = "title";
        final String MOVIE_RELEASE_DATE = "release_date";
        final String MOVIE_VOTE_AVERAGE = "vote_average";
        final String MOVIE_PLOT = "overview";
        final String MOVIES_POSTER_PATH = "poster_path";

        final String urlBaseCover = "http://image.tmdb.org/t/p/w185";

        JSONObject movieJson = new JSONObject(movieJsonStr);

        String movieTitle;
        String movieReleaseDate;
        Double movieVoteAverage;
        String moviePlot;
        String pathMovieCover;

        movieTitle = movieJson.getString(MOVIE_TITLE);
        movieReleaseDate = movieJson.getString(MOVIE_RELEASE_DATE);
        movieVoteAverage = movieJson.getDouble(MOVIE_VOTE_AVERAGE);
        moviePlot = movieJson.getString(MOVIE_PLOT);

        pathMovieCover = urlBaseCover.concat(movieJson.getString(MOVIES_POSTER_PATH));

        MovieDetails movieDetails = new MovieDetails(movieTitle, movieReleaseDate, movieVoteAverage, moviePlot, pathMovieCover);

        return movieDetails;
    }
}
