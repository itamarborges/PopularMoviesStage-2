package com.example.itamarborges.popularmoviesstage2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.itamarborges.popularmoviesstage2.pojo.MovieDetails;
import com.example.itamarborges.popularmoviesstage2.pojo.MovieReview;
import com.example.itamarborges.popularmoviesstage2.pojo.MovieVideo;
import com.example.itamarborges.popularmoviesstage2.utils.NetworkUtils;
import com.squareup.picasso.Picasso;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieDetailsActivity extends AppCompatActivity implements MovieDetailsAsyncTask.MovieLoad,
                                                                       MovieVideosAsyncTask.MovieVideosLoad,
                                                                       MovieReviewsAsyncTask.MovieReviewsLoad {

    public final static String INTENT_KEY_ID = "key_id";

    @BindView(R.id.tv_movie_title) TextView mMovieTitle;
    @BindView(R.id.tv_movie_runtime) TextView mMovieRuntime;
    @BindView(R.id.tv_movie_tagline) TextView mMovieTagline;
    @BindView(R.id.tv_movie_release_date) TextView mMovieReleaseDate;
    @BindView(R.id.tv_movie_vote_average) TextView mMovieVoteAverage;
    @BindView(R.id.tv_movie_plot) TextView mMoviePlot;
    @BindView(R.id.iv_movie_poster) ImageView mMovieCover;
    @BindView(R.id.rv_videos) RecyclerView mRecyclerVideos;
    @BindView(R.id.rv_reviews) RecyclerView mRecyclerReviews;

    MovieVideosListAdapter mMovieVideosListAdapter;
    MovieReviewsListAdapter mMovieReviewsListAdapter;

    private int movieId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        ButterKnife.bind(this);

        LinearLayoutManager layoutManagerVideos = new LinearLayoutManager(this);
        mRecyclerVideos.setLayoutManager(layoutManagerVideos);

        LinearLayoutManager layoutManagerReviews = new LinearLayoutManager(this);
        mRecyclerReviews.setLayoutManager(layoutManagerReviews);

        mMovieVideosListAdapter = new MovieVideosListAdapter(null);
        mRecyclerVideos.setAdapter(mMovieVideosListAdapter);
        mRecyclerVideos.setHasFixedSize(false);

        mMovieReviewsListAdapter = new MovieReviewsListAdapter(null);
        mRecyclerReviews.setAdapter(mMovieReviewsListAdapter);

        Intent intent = getIntent();

        if (intent != null) {
            movieId = intent.getIntExtra(INTENT_KEY_ID, -1);
            makeMovieQuery();
            makeMovieVideosQuery();
            makeMovieReviewsQuery();
        }
    }

    private void makeMovieQuery() {
        URL githubSearchUrl = NetworkUtils.buildUrl(String.valueOf(movieId));
        MovieDetailsAsyncTask mMovieDetailsAsyncTask = new MovieDetailsAsyncTask(this);
        mMovieDetailsAsyncTask.execute(githubSearchUrl);
    }

    private void makeMovieVideosQuery() {
        URL githubSearchUrl = NetworkUtils.buildUrlAditionalPath(String.valueOf(movieId), NetworkUtils.THE_MOVIE_DB_VIDEOS);
        MovieVideosAsyncTask mMovieVideosDetailsAsyncTask = new MovieVideosAsyncTask(this);
        mMovieVideosDetailsAsyncTask.execute(githubSearchUrl);
    }

    private void makeMovieReviewsQuery() {
        URL githubSearchUrl = NetworkUtils.buildUrlAditionalPath(String.valueOf(movieId), NetworkUtils.THE_MOVIE_DB_REVIEWS);
        MovieReviewsAsyncTask mMovieReviewsDetailsAsyncTask = new MovieReviewsAsyncTask(this);
        mMovieReviewsDetailsAsyncTask.execute(githubSearchUrl);
    }

    @Override
    public void onMovieHasLoaded(MovieDetails movieDetails) {
        mMovieTitle.setText(movieDetails.getTitle());
        try{
            mMovieReleaseDate.setText(String.valueOf(new SimpleDateFormat("yyyy").format(new SimpleDateFormat("yyyy-m-d").parse(movieDetails.getReleaseDate()))));
        } catch (Exception e) {
            e.printStackTrace();
        }
        mMovieVoteAverage.setText(String.valueOf(movieDetails.getVoteAverage()).concat("/10"));
        mMoviePlot.setText(movieDetails.getPlot());
        Picasso.with(this).load(movieDetails.getUrlCover()).into(mMovieCover);
        mMovieRuntime.setText(String.valueOf(movieDetails.getRuntime().intValue()).concat("min"));
        mMovieTagline.setText(movieDetails.getTagline());
    }

    @Override
    public void onMovieVideosHasLoaded(List<MovieVideo> movieVideos) {
        mMovieVideosListAdapter.setMovieVideos(movieVideos);
    }

    @Override
    public void onMovieReviewsHasLoaded(List<MovieReview> movieReviews) {
        mMovieReviewsListAdapter.setMovieReviews(movieReviews);
    }

}
