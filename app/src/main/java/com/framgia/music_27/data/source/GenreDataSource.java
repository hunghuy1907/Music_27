package com.framgia.music_27.data.source;

import com.framgia.music_27.data.model.Genre;
import java.util.List;

public interface GenreDataSource {

    void getGenres(CallBack<List<Genre>> callBack);
}
