package com.example.itamarborges.popularmoviesstage2.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by itamarborges on 21/02/18.
 */

public class MoviesDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "moviesDb.db";

    private static final int VERSION = 1;

    public MoviesDbHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        // Create tasks table (careful to follow SQL formatting rules)
        final String CREATE_TABLE = "CREATE TABLE "  + MoviesContract.FavoriteEntry.TABLE_NAME + " (" +
                MoviesContract.FavoriteEntry._ID                + " INTEGER PRIMARY KEY, " +
                MoviesContract.FavoriteEntry.COLUMN_ID + " TEXT NOT NULL, " +
                MoviesContract.FavoriteEntry.COLUMN_TITLE + " TEXT NOT NULL, " +
                MoviesContract.FavoriteEntry.COLUMN_URL_COVER    + " TEXT NOT NULL);";

        db.execSQL(CREATE_TABLE);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + MoviesContract.FavoriteEntry.TABLE_NAME);
        onCreate(db);
    }
}
