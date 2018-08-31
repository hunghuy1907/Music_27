package com.framgia.music_27.data.repository;

import android.support.annotation.NonNull;
import com.framgia.music_27.data.model.Genre;
import com.framgia.music_27.data.model.Track;
import com.framgia.music_27.data.source.CallBack;
import com.framgia.music_27.data.source.TrackDataSource;
import com.framgia.music_27.data.source.remote.TrackRemoteDataResource;
import java.util.List;

public class TrackRepository implements TrackDataSource.localDataSource,
        TrackDataSource.remoteDataSource {

    public static TrackRepository sInstance;
    private TrackRemoteDataResource mTrackRemoteDataResource;

    private TrackRepository(TrackRemoteDataResource trackRemoteDataResource) {
        mTrackRemoteDataResource = trackRemoteDataResource;
    }

    public static TrackRepository getInstance(TrackRemoteDataResource trackRemoteDataResource ){
        if (sInstance == null) {
            sInstance = new TrackRepository(trackRemoteDataResource);
        }
        return sInstance;
    }

    @Override
    public void getTracks(@NonNull CallBack<List<Track>> callback) {
        mTrackRemoteDataResource.getTracks(callback);
    }

    @Override
    public void getTracks(String genre, String type, @NonNull CallBack<List<Track>> callback) {
        mTrackRemoteDataResource.getTracks(genre, type, callback);
    }

    @Override
    public void getTracksByGenre(String type, @NonNull CallBack<Genre> callback) {

    }

    @Override
    public void getTracksByGenre(String type, List<Genre> genres,
            @NonNull CallBack<List<Genre>> callback) {
        mTrackRemoteDataResource.getTracksByGenre(type, genres, callback);
    }
}
