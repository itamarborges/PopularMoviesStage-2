package com.example.itamarborges.popularmoviesstage2;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.itamarborges.popularmoviesstage2.pojo.MovieCover;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by itamarborges on 28/12/17.
 */

public class MoviesListAdapter extends RecyclerView.Adapter<MoviesListAdapter.MovieListViewHolder> {

    private static final String TAG = MoviesListAdapter.class.getSimpleName();

    public List<MovieCover> getMoviesCover() {
        return mMoviesCover;
    }

    public void setMoviesCover(List<MovieCover> mMoviesCover) {
        this.mMoviesCover = mMoviesCover;
    }

    private List<MovieCover> mMoviesCover;

    public MoviesListAdapter(List<MovieCover> moviesCover) {
        mMoviesCover = moviesCover;
    }

    @Override
    public MovieListViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.movie_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        MovieListViewHolder viewHolder = new MovieListViewHolder(view, context);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MovieListViewHolder holder, int position) {
        holder.bind(mMoviesCover.get(position));
    }

    @Override
    public int getItemCount() {
        return mMoviesCover.size();
    }

    class MovieListViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        @BindView(R.id.iv_movie_cover) ImageView mMovieCover;

        Context mContext;
        int mMovieId;

        public MovieListViewHolder(View itemView, Context context) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = context;

            itemView.setOnClickListener(this);
        }

        void bind(MovieCover movieCover) {
            Picasso.with(mContext).load(movieCover.getUrlCover()).into(mMovieCover);
            mMovieId = movieCover.getId();
        }


        @Override
        public void onClick(View v) {
            Intent intent = new Intent(mContext, MovieDetailsActivity.class);
            intent.putExtra(MovieDetailsActivity.INTENT_KEY_ID, mMovieId);

            mContext.startActivity(intent);
        }
    }
}
