package com.example.itamarborges.popularmoviesstage2;

import android.os.AsyncTask;

import com.example.itamarborges.popularmoviesstage2.pojo.MovieReview;
import com.example.itamarborges.popularmoviesstage2.utils.MoviesDBJsonUtils;
import com.example.itamarborges.popularmoviesstage2.utils.NetworkUtils;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by itamarborges on 29/12/17.
 */

public class MovieReviewsAsyncTask extends AsyncTask<URL, Void, List<MovieReview>> {

    private static final String TAG = MovieReviewsAsyncTask.class.getSimpleName();
    MovieReviewsLoad mMovieReviews;

    public interface MovieReviewsLoad {
        void onMovieReviewsHasLoaded(List<MovieReview> movieReviews);
    }

    public MovieReviewsAsyncTask(MovieReviewsLoad moviesListAdapter) {
        mMovieReviews = moviesListAdapter;
    }

    @Override
    protected List<MovieReview> doInBackground(URL... params) {

        URL searchUrl = params[0];
        List<MovieReview> movieReviews = new ArrayList<>();
        String queryResult = null;
        try {
            queryResult = NetworkUtils.getResponseFromHttpUrl(searchUrl);

            movieReviews = MoviesDBJsonUtils.getMovieReviews(queryResult);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return movieReviews;
    }

    @Override
    protected void onPostExecute(List<MovieReview> movieReviews) {
        super.onPostExecute(movieReviews);

        mMovieReviews.onMovieReviewsHasLoaded(movieReviews);
    }
}
