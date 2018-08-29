package com.framgia.music_27.data.source.remote;

import com.framgia.music_27.data.model.Genre;
import com.framgia.music_27.data.source.CallBack;
import com.framgia.music_27.data.source.GenreDataSource;
import java.util.List;

public class GenreRemoteDataSource implements GenreDataSource{

    private static GenreRemoteDataSource sInstance;

    public static GenreRemoteDataSource getInstance() {
        if (sInstance == null) {
            sInstance = new GenreRemoteDataSource();
        }
        return sInstance;
    }

    @Override
    public void getGenres(CallBack<List<Genre>> callBack) {

    }
}
