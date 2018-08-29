package com.framgia.music_27.data.source.remote;

import android.support.annotation.NonNull;
import com.framgia.music_27.data.model.Track;
import com.framgia.music_27.data.source.CallBack;
import com.framgia.music_27.data.source.TrackDataSource;
import java.util.List;

public class TrackRemoteDataResource implements TrackDataSource.remoteDataSource {
    public static TrackRemoteDataResource sInstance;

    public static TrackRemoteDataResource getInstance() {
        if (sInstance == null) {
            sInstance = new TrackRemoteDataResource();
        }
        return sInstance;
    }

    @Override
    public void getTracks(@NonNull CallBack<List<Track>> callback) {

    }

    @Override
    public void getTracksByGenre(String genre, int page, @NonNull CallBack<List<Track>> callback) {

    }
}
