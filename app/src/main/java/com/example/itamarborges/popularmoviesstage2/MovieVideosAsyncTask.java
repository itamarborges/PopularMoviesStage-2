package com.example.itamarborges.popularmoviesstage2;

import android.os.AsyncTask;

import com.example.itamarborges.popularmoviesstage2.pojo.MovieVideo;
import com.example.itamarborges.popularmoviesstage2.utils.MoviesDBJsonUtils;
import com.example.itamarborges.popularmoviesstage2.utils.NetworkUtils;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by itamarborges on 29/12/17.
 */

public class MovieVideosAsyncTask extends AsyncTask<URL, Void, List<MovieVideo>> {

    private static final String TAG = MovieVideosAsyncTask.class.getSimpleName();
    MovieVideosLoad mMovieVideosLoad;

    public interface MovieVideosLoad {
        void onMovieVideosHasLoaded(List<MovieVideo> movieVideos);
    }

    public MovieVideosAsyncTask(MovieVideosLoad moviesListAdapter) {
        mMovieVideosLoad = moviesListAdapter;
    }

    @Override
    protected List<MovieVideo> doInBackground(URL... params) {

        URL searchUrl = params[0];
        List<MovieVideo> movieVideos = new ArrayList<>();
        String queryResult = null;
        try {
            queryResult = NetworkUtils.getResponseFromHttpUrl(searchUrl);

            movieVideos = MoviesDBJsonUtils.getMovieVideos(queryResult);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return movieVideos;
    }

    @Override
    protected void onPostExecute(List<MovieVideo> movieVideos) {
        super.onPostExecute(movieVideos);

        mMovieVideosLoad.onMovieVideosHasLoaded(movieVideos);
    }
}
