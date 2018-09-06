package com.framgia.music_27.data.source.remote;

import android.support.annotation.NonNull;
import com.framgia.music_27.BuildConfig;
import com.framgia.music_27.data.model.Genre;
import com.framgia.music_27.data.source.CallBack;
import com.framgia.music_27.data.source.TrackDataSource;
import com.framgia.music_27.utils.Constants;
import java.util.List;

public class TrackRemoteDataResource implements TrackDataSource.remoteDataSource {
    public static TrackRemoteDataResource sInstance;

    public static TrackRemoteDataResource getInstance() {
        if (sInstance == null) {
            sInstance = new TrackRemoteDataResource();
        }
        return sInstance;
    }


    private void getdataFromApi(String type,  List<Genre> genres,
            @NonNull CallBack<List<Genre>> callback) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(Constants.SoundClound.BASE_URL)
                .append(Constants.SoundClound.PARAM_KIND)
                .append(Constants.SoundClound.PARAM_GENRE)
                .append(Constants.SoundClound.PARAM_TYPE)
                .append(type)
                .append(Constants.SoundClound.PARAM_CLIENT_ID)
                .append(BuildConfig.API_KEY)
                .append(Constants.SoundClound.PARAM_LIMIT)
                .append(Constants.SoundClound.LIMIT);
        String url = stringBuilder.toString();
        new TrackRemoteAsynTask(callback, type, genres).execute(url);
    }

    private void getGenreByTrackFromApi(String type, CallBack callBack) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(Constants.SoundClound.BASE_URL)
                .append(Constants.SoundClound.PARAM_KIND)
                .append(Constants.SoundClound.PARAM_GENRE)
                .append(Constants.SoundClound.PARAM_TYPE)
                .append(type)
                .append(Constants.SoundClound.PARAM_CLIENT_ID)
                .append(BuildConfig.API_KEY);
        String url = stringBuilder.toString();
        new GenreRemoteAsynTask(callBack, type).execute(url);
    }

    public static String getStreamUrl(int id) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(Constants.Stream.STREAM_URL)
                .append(String.valueOf(id))
                .append(Constants.Stream.STREAM)
                .append(Constants.Stream.STREAM_CLIENT_ID)
                .append(BuildConfig.API_KEY);
        return stringBuilder.toString();
    }

    @Override
    public void getTracksByGenre(String type, List<Genre> genres,
            @NonNull CallBack<List<Genre>> callback) {
        getdataFromApi(type, genres, callback);
    }

    @Override
    public void getGenres(String type, @NonNull CallBack<Genre> callBack) {
        getGenreByTrackFromApi(type, callBack);
    }
}
