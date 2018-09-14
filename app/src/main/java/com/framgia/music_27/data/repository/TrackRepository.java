package com.framgia.music_27.data.repository;

import android.content.Context;
import android.support.annotation.NonNull;
import com.framgia.music_27.data.model.Genre;
import com.framgia.music_27.data.model.Track;
import com.framgia.music_27.data.source.CallBack;
import com.framgia.music_27.data.source.TrackDataSource;
import com.framgia.music_27.data.source.local.TrackLocalDataSource;
import com.framgia.music_27.data.source.remote.TrackRemoteDataResource;
import java.util.List;

public class TrackRepository implements TrackDataSource.localDataSource,
        TrackDataSource.remoteDataSource {

    public static TrackRepository sInstance;
    private TrackRemoteDataResource mTrackRemoteDataResource;
    private TrackLocalDataSource mTrackLocalDataSource;

    private TrackRepository(TrackRemoteDataResource trackRemoteDataResource,
            TrackLocalDataSource trackLocalDataSource) {
        mTrackRemoteDataResource = trackRemoteDataResource;
        mTrackLocalDataSource = trackLocalDataSource;
    }

    public static TrackRepository getInstance(TrackRemoteDataResource trackRemoteDataResource,
            TrackLocalDataSource trackLocalDataSource){
        if (sInstance == null) {
            sInstance = new TrackRepository(trackRemoteDataResource, trackLocalDataSource);
        }
        return sInstance;
    }

    @Override
    public void getTracksByGenre(String type, List<Genre> genres,
            @NonNull CallBack<List<Genre>> callback) {
        mTrackRemoteDataResource.getTracksByGenre(type, genres, callback);
    }

    @Override
    public void getGenres(String type, @NonNull CallBack<Genre> callBack) {
        mTrackRemoteDataResource.getGenres(type, callBack);
    }

    @Override
    public void searchTrackByKey(String key, CallBack<List<Track>> callBack) {
        mTrackRemoteDataResource.searchTrackByKey(key, callBack);
    }

    @Override
    public void getTrack(CallBack<List<Track>> callBack) {
        mTrackLocalDataSource.getTrack(callBack);
    }

    @Override
    public void getTotalLocalMusic(CallBack<Integer> callBack) {
        mTrackLocalDataSource.getTotalLocalMusic(callBack);
    }

    @Override
    public void getDownloadTrack(String type, CallBack callBack) {
        mTrackLocalDataSource.getDownloadTrack(type, callBack);
    }
}
