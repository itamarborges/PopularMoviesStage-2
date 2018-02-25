package com.example.itamarborges.popularmoviesstage2.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;

import com.example.itamarborges.popularmoviesstage2.data.MoviesContract;
import com.example.itamarborges.popularmoviesstage2.pojo.MovieCover;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by itamarborges on 21/02/18.
 */

public class MovieModel {

    public Uri insert(Context context, String id, String title, String urlCover) {

        ContentValues contentValues = new ContentValues();

        contentValues.put(MoviesContract.FavoriteEntry.COLUMN_ID, id);
        contentValues.put(MoviesContract.FavoriteEntry.COLUMN_TITLE, title);
        contentValues.put(MoviesContract.FavoriteEntry.COLUMN_URL_COVER, urlCover);

        Uri uri = context.getContentResolver().insert(MoviesContract.FavoriteEntry.CONTENT_URI, contentValues);

        return uri;
    }

    public int delete(Context context, String id) {

        Uri uriToDelete = MoviesContract.FavoriteEntry.CONTENT_URI.buildUpon().appendPath(id).build();

        int deletedRows = context.getContentResolver().delete(uriToDelete, null, null);

        return deletedRows;
    }

    public List<MovieCover> listMoviesCover(Cursor cursor) {

        int idCol = cursor.getColumnIndex(MoviesContract.FavoriteEntry.COLUMN_ID);
        int titleCol = cursor.getColumnIndex(MoviesContract.FavoriteEntry.COLUMN_TITLE);
        int urlCoverCol = cursor.getColumnIndex(MoviesContract.FavoriteEntry.COLUMN_URL_COVER);

        List<MovieCover> mMoviesCover = new ArrayList<>();

        while (cursor.moveToNext()) {
            MovieCover movieCover = new MovieCover(cursor.getInt(idCol), cursor.getString(urlCoverCol), cursor.getString(titleCol));
            mMoviesCover.add(movieCover);
        }
        return mMoviesCover;
    }

    public boolean isFavorite(Context context, String id) {

        Uri uriToSelect = MoviesContract.FavoriteEntry.CONTENT_URI.buildUpon().appendPath(id).build();

        Cursor c = context.getContentResolver().query(MoviesContract.FavoriteEntry.CONTENT_URI,
                null,
                null,
                null,
                null);

        return c.moveToNext();
    }
}
