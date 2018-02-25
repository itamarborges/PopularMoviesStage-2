package com.example.itamarborges.popularmoviesstage2;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.preference.PreferenceManager;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;

import com.example.itamarborges.popularmoviesstage2.data.MoviesContract;
import com.example.itamarborges.popularmoviesstage2.model.MovieModel;
import com.example.itamarborges.popularmoviesstage2.pojo.MovieCover;
import com.example.itamarborges.popularmoviesstage2.utils.NetworkUtils;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemSelected;

interface ShowElements {
    void showProgressBar();
    void showErrorMessage();
    void showMoviesList();
}


public class MainActivity extends AppCompatActivity implements ShowElements, LoaderManager.LoaderCallbacks<Cursor>{

    private MoviesListAdapter mAdapter;

    private static final String SPINNER_INDEX = "spinnerIndex";

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final int MOVIES_LOADER_ID = 0;

    MovieModel movieModel = new MovieModel();

    private List<MovieCover> mMoviesCover = new ArrayList<>();

    @BindView(R.id.rv_movies_cover) RecyclerView mMoviesCoverList;
    @BindView(R.id.sp_sort_criteria) Spinner mSpinnerSort;
    @BindView(R.id.btn_try_again) Button mBtnTryAgain;
    @BindView(R.id.pb_loading_indicator) ProgressBar mProgressBar;
    @BindView(R.id.layout_error) LinearLayout mErrorLayout;

    @OnClick(R.id.btn_try_again)
    public void btnTryAgain() {
        makeMoviesQuery(mSpinnerSort.getSelectedItemPosition());
    }

    @OnItemSelected(R.id.sp_sort_criteria)
    public void spinnerItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        makeMoviesQuery(i);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        mMoviesCoverList.setLayoutManager(layoutManager);

        mAdapter = new MoviesListAdapter(new ArrayList<MovieCover>());
        mMoviesCoverList.setAdapter(mAdapter);

        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey(SPINNER_INDEX)) {
                int spinnerIndex = savedInstanceState.getInt(SPINNER_INDEX);
                mSpinnerSort.setSelection(spinnerIndex,true);
            }
        }

        mBtnTryAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makeMoviesQuery(mSpinnerSort.getSelectedItemPosition());
            }
        });

        getSupportLoaderManager().initLoader(MOVIES_LOADER_ID, null, this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        mSpinnerSort.setSelection(sp.getInt(SPINNER_INDEX, 0));

        getSupportLoaderManager().restartLoader(MOVIES_LOADER_ID, null, this);
    }

    private void makeMoviesQuery(int option) {
        if (NetworkUtils.isNetworkAvailable(getApplicationContext())) {
            if (option == 2) {
                mAdapter.setMoviesCover(mMoviesCover);
                mAdapter.notifyDataSetChanged();
                showMoviesList();

            } else {
                URL githubSearchUrl = NetworkUtils.buildUrl(option == 0 ? NetworkUtils.THE_MOVIE_DB_SORT_HIGHEST_RATED: NetworkUtils.THE_MOVIE_DB_SORT_POPULAR);
                MoviesAsyncTask mMoviesAsyncTask = new MoviesAsyncTask(mAdapter, this);
                mMoviesAsyncTask.execute(githubSearchUrl);
            }
        } else {
            showErrorMessage();
        }
    }

    @Override
    public void showProgressBar() {
        mMoviesCoverList.setVisibility(View.GONE);
        mProgressBar.setVisibility(View.VISIBLE);
        mErrorLayout.setVisibility(View.GONE);
    }

    @Override
    public void showErrorMessage() {
        mMoviesCoverList.setVisibility(View.GONE);
        mProgressBar.setVisibility(View.GONE);
        mErrorLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void showMoviesList() {
        mMoviesCoverList.setVisibility(View.VISIBLE);
        mProgressBar.setVisibility(View.GONE);
        mErrorLayout.setVisibility(View.GONE);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle loaderArgs) {
        return new AsyncTaskLoader<Cursor>(this) {

            Cursor mMoviesData = null;

            @Override
            protected void onStartLoading() {
                if (mMoviesData != null) {
                    deliverResult(mMoviesData);
                } else {
                    forceLoad();
                }
            }

            @Override
            public Cursor loadInBackground() {
                try {
                    return getContentResolver().query(MoviesContract.FavoriteEntry.CONTENT_URI,
                            null,
                            null,
                            null,
                            null);

                } catch (Exception e) {
                    Log.e(TAG, "Failed to asynchronously load data.");
                    e.printStackTrace();
                    return null;
                }
            }

            // deliverResult sends the result of the load, a Cursor, to the registered listener
            public void deliverResult(Cursor data) {
                mMoviesData = data;
                super.deliverResult(data);
            }
        };
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mMoviesCover = movieModel.listMoviesCover(data);
        if (mSpinnerSort.getSelectedItemPosition() == 2) {
            makeMoviesQuery(mSpinnerSort.getSelectedItemPosition());
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        loader = null;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {

        super.onSaveInstanceState(outState);

        int spinnerIndex = mSpinnerSort.getSelectedItemPosition();
        outState.putInt(SPINNER_INDEX, spinnerIndex);

    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = sp.edit();

        editor.putInt(SPINNER_INDEX, mSpinnerSort.getSelectedItemPosition());

        editor.apply();

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState.containsKey(SPINNER_INDEX)) {
            int spinnerIndex = savedInstanceState.getInt(SPINNER_INDEX);
            mSpinnerSort.setSelection(spinnerIndex, true);
        }
    }


}
