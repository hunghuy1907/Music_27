package com.framgia.music_27.data.repository;

import com.framgia.music_27.data.model.Genre;
import com.framgia.music_27.data.source.CallBack;
import com.framgia.music_27.data.source.GenreDataSource;
import java.util.List;

public class GenreRepository implements GenreDataSource {

    private static GenreRepository sInstance;
    private GenreDataSource mGenresDataSource;

    private GenreRepository(GenreDataSource genreDataSource){
        mGenresDataSource = genreDataSource;
    }

    public static GenreRepository getInstance(GenreDataSource genresLocalDataSource) {
        if (sInstance == null) {
            sInstance = new GenreRepository(genresLocalDataSource);
        }
        return sInstance;
    }

    @Override
    public void getGenres(CallBack<List<Genre>> callBack) {
        mGenresDataSource.getGenres(callBack);
    }
}
