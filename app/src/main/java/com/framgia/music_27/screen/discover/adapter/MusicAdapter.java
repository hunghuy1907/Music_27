package com.framgia.music_27.screen.discover.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.framgia.music_27.R;
import com.framgia.music_27.data.model.Track;
import java.util.List;

public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.TrackHolder>{

    private Context mContext;
    private List<Track> mTracks;
    private LayoutInflater mInflater;

    public MusicAdapter(Context context, List<Track> Tracks){
        mTracks = Tracks;
        mContext = context;
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public TrackHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_music, parent, false);
        return new TrackHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TrackHolder holder, int position) {
        holder.fillData(mContext, mTracks.get(position));
    }

    @Override
    public int getItemCount() {
        return mTracks == null ? 0 : mTracks.size();
    }

    public static class TrackHolder extends RecyclerView.ViewHolder {
        private ImageView mImageTrack;
        private TextView mTextNameSong;
        private TextView mTextSinger;

        public TrackHolder(View itemView) {
            super(itemView);
            mTextNameSong = itemView.findViewById(R.id.text_name_song);
            mTextSinger = itemView.findViewById(R.id.text_singer);
            mImageTrack = itemView.findViewById(R.id.image_music);
        }
        private void fillData(Context context, Track track) {
            mTextNameSong.setText(track.getTitle());
            mTextSinger.setText(track.getArtist());
            Glide.with(context)
                    .load(track.getArtworkUrl())
                    .apply(new RequestOptions().placeholder(R.drawable.ic_music_replace)
                                                .error(R.drawable.ic_no_image))
                    .into(mImageTrack);
        }
    }
}
