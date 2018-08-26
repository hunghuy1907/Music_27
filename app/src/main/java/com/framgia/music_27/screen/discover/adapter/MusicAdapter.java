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
import com.framgia.music_27.data.model.Music;
import java.util.List;

public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.MusicHolder>{

    private Context mContext;
    private List<Music> mMusics;
    private LayoutInflater mInflater;

    public MusicAdapter(Context context, List<Music> musics){
        mMusics = musics;
        mContext = context;
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MusicHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_music, parent, false);
        return new MusicHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MusicHolder holder, int position) {
        holder.fillData(mContext, mMusics.get(position));
    }

    @Override
    public int getItemCount() {
        return mMusics == null ? 0 : mMusics.size();
    }

    public static class MusicHolder extends RecyclerView.ViewHolder {
        private ImageView mImageMusic;
        private TextView mTextNameSong;
        private TextView mTextSinger;

        public MusicHolder(View itemView) {
            super(itemView);
            mTextNameSong = itemView.findViewById(R.id.text_name_song);
            mTextSinger = itemView.findViewById(R.id.text_singer);
            mImageMusic = itemView.findViewById(R.id.image_music);
        }
        private void fillData(Context context, Music music) {
            mTextNameSong.setText(music.getSong());
            mTextSinger.setText(music.getSinger());
            Glide.with(context)
                    .load(music.getImagePath())
                    .apply(new RequestOptions().placeholder(R.drawable.ic_music_replace)
                                                .error(R.drawable.ic_no_image))
                    .into(mImageMusic);
        }
    }
}
