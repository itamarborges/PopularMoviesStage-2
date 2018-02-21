package com.example.itamarborges.popularmoviesstage2;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.itamarborges.popularmoviesstage2.pojo.MovieReview;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by itamarborges on 28/12/17.
 */

public class MovieReviewsListAdapter extends RecyclerView.Adapter<MovieReviewsListAdapter.MovieListViewHolder> {

    private static final String TAG = MovieReviewsListAdapter.class.getSimpleName();

    public List<MovieReview> getMovieReviews() {
        return mMoviesReviews;
    }

    public void setMovieReviews(List<MovieReview> mMoviesReviews) {
        this.mMoviesReviews = mMoviesReviews;
        notifyDataSetChanged();
    }

    private List<MovieReview> mMoviesReviews;

    public MovieReviewsListAdapter(List<MovieReview> movieReviews) {mMoviesReviews = movieReviews;
    }

    @Override
    public MovieListViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.movie_reviews_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        MovieListViewHolder viewHolder = new MovieListViewHolder(view, context);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MovieListViewHolder holder, int position) {
        holder.bind(mMoviesReviews.get(position));
    }

    @Override
    public int getItemCount() {
        return (mMoviesReviews == null) ? 0 : mMoviesReviews.size();
    }

    class MovieListViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.txtContent) TextView mTxtContent;
        @BindView(R.id.txtAuthor) TextView mTxtAuthor;

        Context mContext;
        int mMovieId;

        public MovieListViewHolder(View itemView, Context context) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = context;
        }

        void bind(MovieReview movieReview) {
            mTxtContent.setText("\"".concat(movieReview.getContent()).concat("\""));
            mTxtAuthor.setText(movieReview.getAuthor());
        }
    }
}
