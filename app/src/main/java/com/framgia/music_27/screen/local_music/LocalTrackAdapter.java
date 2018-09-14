package com.framgia.music_27.screen.local_music;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.framgia.music_27.R;
import com.framgia.music_27.data.model.Track;
import java.util.List;

public class LocalTrackAdapter extends RecyclerView.Adapter<LocalTrackAdapter.LocalTrackHolder> {
    private Context mContext;
    private List<Track> mTracks;
    private LayoutInflater mInflater;
    private OnClickTrackLocal mOnClickTrackLocal;

    public LocalTrackAdapter(Context context, List<Track> Tracks, OnClickTrackLocal onClickTrackLocal) {
        mContext = context;
        mTracks = Tracks;
        mInflater = LayoutInflater.from(context);
        mOnClickTrackLocal = onClickTrackLocal;
    }

    @NonNull
    @Override
    public LocalTrackHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_local_track, parent, false);
        return new LocalTrackHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LocalTrackHolder holder, int position) {
        holder.fillData(mTracks.get(position), mOnClickTrackLocal);
    }

    @Override
    public int getItemCount() {
        return mTracks == null ? 0 : mTracks.size();
    }

    public  class LocalTrackHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {
        private TextView mTextNameSong;
        private TextView mTextArtist;
        private OnClickTrackLocal mOnClickTrackLocal;
        private ConstraintLayout mLayoutTrack;

        public LocalTrackHolder(View itemView) {
            super(itemView);
            mTextNameSong = itemView.findViewById(R.id.text_name_song_local);
            mTextArtist = itemView.findViewById(R.id.text_artist_local);
            mLayoutTrack = itemView.findViewById(R.id.layout_item_track_local);
            mLayoutTrack.setOnClickListener(this);
        }

        private void fillData(Track track, OnClickTrackLocal onClickTrackLocal) {
            mOnClickTrackLocal = onClickTrackLocal;
            mTextNameSong.setText(track.getTitle());
            mTextArtist.setText(track.getArtist());
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.layout_item_track_local:
                    mOnClickTrackLocal.clickTrackLocal(getAdapterPosition());
                default:
                    break;
            }
        }
    }
}
