package com.framgia.music_27.data.source;

import android.support.annotation.NonNull;
import com.framgia.music_27.data.model.Track;
import java.util.List;

public interface TrackDataSource {

    public interface remoteDataSource {

        void getTracks(@NonNull CallBack<List<Track>> callback);

        void getTracksByGenre(String genre, int page,
                @NonNull CallBack<List<Track>> callback);
    }

    public interface localDataSource {

    }
}
