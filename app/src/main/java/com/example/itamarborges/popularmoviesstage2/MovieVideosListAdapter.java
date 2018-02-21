package com.example.itamarborges.popularmoviesstage2;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.itamarborges.popularmoviesstage2.pojo.MovieVideo;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by itamarborges on 28/12/17.
 */

public class MovieVideosListAdapter extends RecyclerView.Adapter<MovieVideosListAdapter.MovieVideosListViewHolder> {

    private static final String TAG = MovieVideosListAdapter.class.getSimpleName();

    public List<MovieVideo> getMoviesVideos() {
        return mMovieVideos;
    }

    public void setMovieVideos(List<MovieVideo> mMovieVideos) {
        this.mMovieVideos = mMovieVideos;
        notifyDataSetChanged();
    }

    private List<MovieVideo> mMovieVideos;

    public MovieVideosListAdapter(List<MovieVideo> movieVideos) {
        mMovieVideos = movieVideos;
    }

    @Override
    public MovieVideosListViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.movie_videos_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        MovieVideosListViewHolder viewHolder = new MovieVideosListViewHolder(view, context);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MovieVideosListViewHolder holder, int position) {
        holder.bind(mMovieVideos.get(position));
    }

    @Override
    public int getItemCount() {
        return (mMovieVideos == null) ? 0 : mMovieVideos.size();
    }

    class MovieVideosListViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        @BindView(R.id.imgBtnPlay) ImageButton mImgBtnPlay;
        @BindView(R.id.txtVideoName) TextView mTxtVideoName;

        Context mContext;
        String mMovieKey;

        public MovieVideosListViewHolder(View itemView, Context context) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = context;

            itemView.setOnClickListener(this);
        }

        void bind(MovieVideo movieVideo) {
            mTxtVideoName.setText(movieVideo.getName());
            mMovieKey = movieVideo.getKey();
        }


        @Override
        public void onClick(View v) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v=".concat(mMovieKey)));
            mContext.startActivity(intent);
        }
    }
}
