package com.framgia.music_27.screen.discover.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.framgia.music_27.R;
import com.framgia.music_27.data.model.Genre;
import com.framgia.music_27.data.model.Track;
import java.util.List;

public class GenresAdapter extends RecyclerView.Adapter<GenresAdapter.GenresHolder> {

    private Context mContext;
    private List<Genre> mGenres;
    private LayoutInflater mInflater;
    private OnClickItem mOnClickItem;
    private MusicAdapter.OnClickItemMusic mOnClickItemMusic;

    public GenresAdapter(Context context, List<Genre> genres, OnClickItem onClickItem,
            MusicAdapter.OnClickItemMusic onClickItemMusic) {
        mGenres = genres;
        mContext = context;
        mOnClickItem = onClickItem;
        mOnClickItemMusic = onClickItemMusic;
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public GenresHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_discover, parent, false);
        return new GenresHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final GenresHolder holder, int position) {
        holder.fillData(mContext, mGenres.get(position), mOnClickItem);
    }

    @Override
    public int getItemCount() {
        return mGenres == null ? 0 : mGenres.size();
    }

    public class GenresHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {
        private TextView mTextNameGenres;
        private RecyclerView mRecyclerMusic;
        private OnClickItem mOnClickItem;
        private TextView mTextSeeAll;

        public GenresHolder(View itemView) {
            super(itemView);
            mTextNameGenres = itemView.findViewById(R.id.text_genres);
            mTextSeeAll = itemView.findViewById(R.id.text_see_all);
            mRecyclerMusic = itemView.findViewById(R.id.recycle_music);
            mTextSeeAll.setOnClickListener(this);
        }

        public void fillData(Context context, Genre genre, OnClickItem onClickItem) {
            mOnClickItem = onClickItem;
            mTextNameGenres.setText(genre.getName());
            initMusicRecycleView(context, genre);
        }

        public void initMusicRecycleView(Context context, Genre genre) {
            List<Track> tracks = genre.getTracks();
            MusicAdapter musicAdapter = new MusicAdapter(context, tracks, mOnClickItemMusic);
            mRecyclerMusic.setLayoutManager(
                    new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
            mRecyclerMusic.setAdapter(musicAdapter);
        }


        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.text_see_all:
                    mOnClickItem.clickItem(getAdapterPosition());
                    break;
                default:
                    break;
            }
        }
    }
}
