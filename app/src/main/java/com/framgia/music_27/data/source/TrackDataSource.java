package com.framgia.music_27.data.source;

import android.content.Context;
import android.support.annotation.NonNull;
import com.framgia.music_27.data.model.Genre;
import com.framgia.music_27.data.model.Track;
import java.util.List;

public interface TrackDataSource {

    public interface remoteDataSource {

        void getTracksByGenre(String type, List<Genre> genres,
                @NonNull CallBack<List<Genre>> callback);

        void getGenres(String type, @NonNull CallBack<Genre> callBack);
    }

    public interface localDataSource {
        void getTrack(CallBack<List<Track>> callBack);

        void getTotalLocalMusic(CallBack<Integer> callBack);

        void getDownloadTrack(String type, CallBack callBack);

    }
}
