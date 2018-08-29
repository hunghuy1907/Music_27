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

public class GenresAdapter extends RecyclerView.Adapter<GenresAdapter.GenresHolder>{

    private Context mContext;
    private List<Genre> mGenres;
    private LayoutInflater mInflater;

    public GenresAdapter(Context context, List<Genre> genres) {
        mGenres = genres;
        mContext = context;
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public GenresHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_discover, parent, false);
        return new GenresHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GenresHolder holder, int position) {
        holder.fillData(mContext, mGenres.get(position));
    }

    @Override
    public int getItemCount() {
        return mGenres == null ? 0 : mGenres.size();
    }

    public static class GenresHolder extends RecyclerView.ViewHolder {
        private TextView mTextSeeAll;
        private TextView mTextNameGenres;
        private RecyclerView mRecyclerMusic;

        public GenresHolder(View itemView) {
            super(itemView);
            mTextNameGenres = itemView.findViewById(R.id.text_genres);
            mTextSeeAll = itemView.findViewById(R.id.text_see_all);
            mRecyclerMusic = itemView.findViewById(R.id.recycle_music);
        }

        public void fillData(Context context, Genre genre) {
            mTextNameGenres.setText(genre.getName());
            initMusicRecycleView(context, genre);
        }

        public void initMusicRecycleView(Context context, Genre genre) {
            List<Track> tracks = genre.getTracks();
            MusicAdapter musicAdapter = new MusicAdapter(context, tracks);
            mRecyclerMusic.setLayoutManager(new LinearLayoutManager(context,
                    LinearLayoutManager.HORIZONTAL, false));
            mRecyclerMusic.setAdapter(musicAdapter);
        }
    }
}
