package com.example.itamarborges.popularmoviesstage2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;

import com.example.itamarborges.popularmoviesstage2.pojo.MovieCover;
import com.example.itamarborges.popularmoviesstage2.utils.NetworkUtils;

import java.net.URL;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemSelected;

interface ShowElements {
    void showProgressBar();
    void showErrorMessage();
    void showMoviesList();
}


public class MainActivity extends AppCompatActivity implements ShowElements {

    private MoviesListAdapter mAdapter;

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

        makeMoviesQuery(0);
    }


    private void makeMoviesQuery(int option) {
        URL githubSearchUrl = NetworkUtils.buildUrl(option == 0 ? NetworkUtils.THE_MOVIE_DB_SORT_HIGHEST_RATED: NetworkUtils.THE_MOVIE_DB_SORT_POPULAR);
        MoviesAsyncTask mMoviesAsyncTask = new MoviesAsyncTask(mAdapter, this);
        mMoviesAsyncTask.execute(githubSearchUrl);
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
}
