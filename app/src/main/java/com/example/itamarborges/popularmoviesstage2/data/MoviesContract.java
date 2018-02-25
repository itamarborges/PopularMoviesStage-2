package com.example.itamarborges.popularmoviesstage2.data;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by itamarborges on 21/02/18.
 */

public class MoviesContract {

    public static final String AUTHORITY = "com.example.itamarborges.popularmoviesstage2";

    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + AUTHORITY);

    public static final String PATH_FAVORITE = "favorite";


    public static final class FavoriteEntry implements BaseColumns {

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_FAVORITE).build();

        public static final String TABLE_NAME = "favorite";

        public static final String COLUMN_ID = "id";
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_URL_COVER = "urlCover";

    }
}
