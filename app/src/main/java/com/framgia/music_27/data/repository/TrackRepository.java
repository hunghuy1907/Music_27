package com.framgia.music_27.data.repository;

import android.support.annotation.NonNull;
import com.framgia.music_27.data.model.Track;
import com.framgia.music_27.data.source.CallBack;
import com.framgia.music_27.data.source.TrackDataSource;
import java.util.List;

public class TrackRepository implements TrackDataSource.localDataSource,
        TrackDataSource.remoteDataSource {

    public static TrackRepository sInstance;
    private TrackDataSource.localDataSource mLocalDataSource;
    private TrackDataSource.remoteDataSource mRemoteDataSource;

    private TrackRepository(TrackDataSource.localDataSource localDataSource,
            TrackDataSource.remoteDataSource remoteDataSource) {
        mLocalDataSource = localDataSource;
        mRemoteDataSource = remoteDataSource;
    }

    public static TrackRepository getInstance(TrackDataSource.localDataSource localDataSource,
            TrackDataSource.remoteDataSource remoteDataSource) {
        if (sInstance == null) {
            sInstance = new TrackRepository(localDataSource, remoteDataSource);
        }
        return sInstance;
    }

    @Override
    public void getTracks(@NonNull CallBack<List<Track>> callback) {
        mRemoteDataSource.getTracks(callback);
    }

    @Override
    public void getTracksByGenre(String genre, int page, @NonNull CallBack<List<Track>> callback) {
        mRemoteDataSource.getTracksByGenre(genre, page, callback);
    }
}
