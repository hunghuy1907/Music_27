package com.framgia.music_27.screen.player;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import com.framgia.music_27.data.model.Track;
import com.framgia.music_27.data.source.remote.TrackRemoteDataResource;
import com.framgia.music_27.service.MediaListener;
import com.framgia.music_27.utils.Constants;
import java.io.IOException;
import java.util.List;

public class PlayerMusicManger implements MediaListener, MediaPlayer.OnPreparedListener {
    private Context mContext;
    private MediaPlayer mMediaPlayer;
    private List<Track> mTracks;
    private int mPosition;
    private OnMediaPlayerChangeListener mOnMediaPlayerChangeListener;

    public PlayerMusicManger(Context context, List<Track> tracks, int position) {
        mContext = context;
        mTracks = tracks;
        this.mPosition = position;
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        mp.start();
    }

    @Override
    public void play(String url) {
        try {
            mMediaPlayer = new MediaPlayer();
            mMediaPlayer.setDataSource(mContext, Uri.parse(url));
            mMediaPlayer.prepareAsync();
            mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mMediaPlayer.setVolume(Constants.Stream.LEFT_VOLUME, Constants.Stream.RIGHT_VOLUME);
            mMediaPlayer.setOnPreparedListener(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stop() {
        mMediaPlayer.stop();
    }

    @Override
    public void pause() {
        if (mMediaPlayer != null && !mMediaPlayer.isPlaying()) {
            mOnMediaPlayerChangeListener.onMediaStateChanged(true);
            mMediaPlayer.start();
        } else {
            mOnMediaPlayerChangeListener.onMediaStateChanged(false);
            mMediaPlayer.pause();
        }
    }

    @Override
    public void next() {
        destroyMusic();
        mPosition++;
        play(TrackRemoteDataResource.getStreamUrl(mTracks.get(mPosition).getId()));
    }

    @Override
    public void release() {
        mMediaPlayer.release();
    }

    @Override
    public void previous() {
        destroyMusic();
        mPosition--;
        play(TrackRemoteDataResource.getStreamUrl(mTracks.get(mPosition).getId()));
    }


    @Override
    public void seek(int duration) {
        mMediaPlayer.seekTo(duration);
    }

    @Override
    public void loop() {

    }

    @Override
    public void shuffle() {

    }

    public void setOnMediaPlayerChangeListener(OnMediaPlayerChangeListener onMediaPlayerChangeListener) {
        mOnMediaPlayerChangeListener = onMediaPlayerChangeListener;
    }

    public void destroyMusic() {
        if (mMediaPlayer != null) {
            mMediaPlayer.stop();
            mMediaPlayer.reset();
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
    }
}
