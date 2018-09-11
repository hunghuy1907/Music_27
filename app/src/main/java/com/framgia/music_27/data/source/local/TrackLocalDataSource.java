package com.framgia.music_27.data.source.local;

import android.content.Context;
import com.framgia.music_27.data.model.Track;
import com.framgia.music_27.data.source.CallBack;
import com.framgia.music_27.data.source.TrackDataSource;
import java.util.List;

public class TrackLocalDataSource implements TrackDataSource.localDataSource{
    private static TrackLocalDataSource sInstance;
    private static Context mContext;

    public static TrackLocalDataSource getsInstance(Context context) {
        mContext = context;
        if (sInstance == null) {
            sInstance = new TrackLocalDataSource();
        }
        return sInstance;
    }

    @Override
    public void getTrack(CallBack<List<Track>> callBack) {
        new GetTrackFromStorage(mContext, callBack).getSongLocal();
    }

    @Override
    public void getTotalLocalMusic(CallBack<Integer> callBack) {
        new GetTrackFromStorage(mContext, callBack).getTotalSongLocal();
    }

    @Override
    public void getDownloadTrack(String type, CallBack callBack) {
        new GetTrackFromDownloadFile(type, callBack);
    }
}
