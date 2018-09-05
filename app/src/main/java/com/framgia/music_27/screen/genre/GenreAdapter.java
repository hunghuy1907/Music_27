package com.framgia.music_27.screen.genre;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.framgia.music_27.R;
import com.framgia.music_27.data.model.Track;
import java.util.List;

public class GenreAdapter extends RecyclerView.Adapter<GenreAdapter.GenreHolder> {
    private Context mContext;
    private List<Track> mTracks;
    private LayoutInflater mInflater;
    private OnClickTrack mOnClickTrack;

    public GenreAdapter(Context context, List<Track> Tracks, OnClickTrack onClickTrack) {
        mContext = context;
        mTracks = Tracks;
        mInflater = LayoutInflater.from(context);
        mOnClickTrack = onClickTrack;
    }

    @NonNull
    @Override
    public GenreHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_genre, parent, false);
        return new GenreHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GenreHolder holder, int position) {
        holder.fillData(mContext, mTracks.get(position), mOnClickTrack);
    }

    @Override
    public int getItemCount() {
        return mTracks == null ? 0 : mTracks.size();
    }

    public static class GenreHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {
        private ImageView mImageMusicGenre;
        private TextView mTextNameSong;
        private TextView mTextArtist;
        private OnClickTrack mOnClickTrack;
        private ImageButton mImageButtonMore;
        private ConstraintLayout mLayoutTrack;

        public GenreHolder(View itemView) {
            super(itemView);
            mTextNameSong = itemView.findViewById(R.id.text_name_song_genre);
            mTextArtist = itemView.findViewById(R.id.text_artist);
            mImageMusicGenre = itemView.findViewById(R.id.image_song_genre);
            mImageButtonMore = itemView.findViewById(R.id.button_more);
            mLayoutTrack = itemView.findViewById(R.id.layout_item_track);
            mLayoutTrack.setOnClickListener(this);
            mImageButtonMore.setOnClickListener(this);
        }

        private void fillData(Context context, Track track, OnClickTrack onClickTrack) {
            mOnClickTrack = onClickTrack;
            mTextNameSong.setText(track.getTitle());
            mTextArtist.setText(track.getArtist());
            Glide.with(context)
                    .load(track.getArtworkUrl())
                    .apply(new RequestOptions().placeholder(R.drawable.ic_music_replace)
                            .error(R.drawable.ic_no_image))
                    .into(mImageMusicGenre);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.button_more:
                    mOnClickTrack.clickButtonMore(getAdapterPosition());

                case R.id.layout_item_track:
                    mOnClickTrack.clickButtonMore(getAdapterPosition());

                default:
                    break;
            }
        }
    }
}
