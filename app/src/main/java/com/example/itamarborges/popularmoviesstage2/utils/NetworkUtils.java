package com.example.itamarborges.popularmoviesstage2.utils;

import android.net.Uri;

import com.example.itamarborges.popularmoviesstage2.BuildConfig;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by itamarborges on 29/12/17.
 */

public class NetworkUtils {

    final static String THE_MOVIE_DB_BASE_URL =
            "http://api.themoviedb.org/3/movie/";

    public final static String THE_MOVIE_DB_SORT_POPULAR = "popular";
    public final static String THE_MOVIE_DB_SORT_HIGHEST_RATED = "top_rated";

    public final static String THE_MOVIE_DB_VIDEOS = "videos";
    public final static String THE_MOVIE_DB_REVIEWS = "reviews";

    final static String THE_MOVIE_DB_API_KEY = "api_key";

    final static String API_KEY = BuildConfig.API_KEY;


    public static URL buildUrl(String path) {
        Uri builtUri = Uri.parse(THE_MOVIE_DB_BASE_URL).buildUpon()
                .appendPath(path)
                .appendQueryParameter(THE_MOVIE_DB_API_KEY, API_KEY)
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }

    public static URL buildUrlAditionalPath(String path, String aditionalPath) {
        Uri builtUri = Uri.parse(THE_MOVIE_DB_BASE_URL).buildUpon()
                .appendPath(path)
                .appendPath(aditionalPath)
                .appendQueryParameter(THE_MOVIE_DB_API_KEY, API_KEY)
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }

    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }
}
