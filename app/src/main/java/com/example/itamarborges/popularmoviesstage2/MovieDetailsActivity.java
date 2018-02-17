package com.example.itamarborges.popularmoviesstage2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.itamarborges.popularmoviesstage2.pojo.MovieDetails;
import com.example.itamarborges.popularmoviesstage2.utils.NetworkUtils;
import com.squareup.picasso.Picasso;

import java.net.URL;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieDetailsActivity extends AppCompatActivity implements MovieDetailsAsyncTask.MovieLoad {

    public final static String INTENT_KEY_ID = "key_id";

    @BindView(R.id.tv_movie_title) TextView mMovieTitle;
    @BindView(R.id.tv_movie_release_date) TextView mMovieReleaseDate;
    @BindView(R.id.tv_movie_vote_average) TextView mMovieVoteAverage;
    @BindView(R.id.tv_movie_plot) TextView mMoviePlot;
    @BindView(R.id.iv_movie_poster) ImageView mMovieCover;

    private int movieId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        ButterKnife.bind(this);

        Intent intent = getIntent();

        if (intent != null) {
            movieId = intent.getIntExtra(INTENT_KEY_ID, -1);
            makeMovieQuery();
        }
    }

    private void makeMovieQuery() {
        URL githubSearchUrl = NetworkUtils.buildUrl(String.valueOf(movieId));
        MovieDetailsAsyncTask mMovieDetailsAsyncTask = new MovieDetailsAsyncTask(this);
        mMovieDetailsAsyncTask.execute(githubSearchUrl);
    }

    @Override
    public void onMovieHasLoaded(MovieDetails movieDetails) {
        mMovieTitle.setText(getString(R.string.movie_title).concat(" " + movieDetails.getTitle()));
        mMovieReleaseDate.setText(getString(R.string.movie_release_date).concat(" " + movieDetails.getReleaseDate()));
        mMovieVoteAverage.setText(getString(R.string.movie_vote_average).concat(" " + String.valueOf(movieDetails.getVoteAverage())));
        mMoviePlot.setText(getString(R.string.movie_plot).concat(" " + movieDetails.getPlot()));
        Picasso.with(this).load(movieDetails.getUrlCover()).into(mMovieCover);

    }
}
