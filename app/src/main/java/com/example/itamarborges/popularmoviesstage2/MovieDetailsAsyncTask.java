package com.example.itamarborges.popularmoviesstage2;

import android.os.AsyncTask;

import com.example.itamarborges.popularmoviesstage2.pojo.MovieDetails;
import com.example.itamarborges.popularmoviesstage2.utils.MoviesDBJsonUtils;
import com.example.itamarborges.popularmoviesstage2.utils.NetworkUtils;

import java.net.URL;

/**
 * Created by itamarborges on 29/12/17.
 */

public class MovieDetailsAsyncTask extends AsyncTask<URL, Void, MovieDetails> {

    private static final String TAG = MovieDetailsAsyncTask.class.getSimpleName();
    MovieLoad mMovieLoad;

    public interface MovieLoad {
        void onMovieHasLoaded(MovieDetails movieDetails);
    }

    public MovieDetailsAsyncTask(MovieLoad moviesListAdapter) {
        mMovieLoad = moviesListAdapter;
    }

    @Override
    protected MovieDetails doInBackground(URL... params) {

        URL searchUrl = params[0];
        MovieDetails movieDetails = null;
        String queryResult = null;
        try {
            queryResult = NetworkUtils.getResponseFromHttpUrl(searchUrl);

            movieDetails = MoviesDBJsonUtils.getMovieDetails(queryResult);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return movieDetails;
    }

    @Override
    protected void onPostExecute(MovieDetails movieDetails) {
        super.onPostExecute(movieDetails);

        mMovieLoad.onMovieHasLoaded(movieDetails);
    }
}
