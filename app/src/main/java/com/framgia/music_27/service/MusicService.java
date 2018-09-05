package com.framgia.music_27.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import com.framgia.music_27.data.model.Track;
import com.framgia.music_27.data.source.remote.TrackRemoteDataResource;
import com.framgia.music_27.screen.player.PlayerMusicManger;
import java.util.ArrayList;
import java.util.List;

public class MusicService extends Service implements MediaListener {
    private static final String EXTRA_TRACK = "EXTRA_TRACK";
    private static final String EXTRA_TRACK_POSITION = "EXTRA_TRACK_POSITION";
    private PlayerMusicManger mPlayerMusic;
    private List<Track> mTracks;
    private int mPosition;

    public static Intent getIntentService(Context context, List<Track> tracks, int position) {
        Intent intent = new Intent(context, MusicService.class);
        intent.putParcelableArrayListExtra(EXTRA_TRACK, (ArrayList<? extends Parcelable>) tracks);
        intent.putExtra(EXTRA_TRACK_POSITION, position);
        return intent;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mPosition = intent.getIntExtra(EXTRA_TRACK_POSITION, 0);
        mTracks = intent.getParcelableArrayListExtra(EXTRA_TRACK);
        mPlayerMusic = new PlayerMusicManger(getApplicationContext(), mTracks, mPosition);
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new MusicBinder();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        mPlayerMusic.release();
        return super.onUnbind(intent);
    }

    @Override
    public void play(String url) {
        mPlayerMusic.play(url);
    }

    @Override
    public void stop() {
        mPlayerMusic.stop();
    }

    @Override
    public void pause() {
        mPlayerMusic.pause();
    }

    @Override
    public void next() {
        mPlayerMusic.next();
    }

    @Override
    public void release() {
        mPlayerMusic.release();
    }

    @Override
    public void previous() {
        mPlayerMusic.previous();
    }

    @Override
    public void seek(int duration) {
        mPlayerMusic.seek(duration);
    }

    @Override
    public void loop() {
        mPlayerMusic.loop();
    }

    @Override
    public void shuffle() {
        mPlayerMusic.shuffle();
    }

    public String getUrlCurrent() {
        return TrackRemoteDataResource.getStreamUrl(mTracks.get(mPosition).getId());
    }

    public class MusicBinder extends Binder {
        public MusicService getService(){
            return MusicService.this;
        }
    }

    public PlayerMusicManger getPlayerMusic() {
        return mPlayerMusic;
    }
}
