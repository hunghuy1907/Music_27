package com.framgia.music_27.data.source.local;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import com.framgia.music_27.data.model.Track;
import com.framgia.music_27.data.source.CallBack;
import java.util.ArrayList;
import java.util.List;

public class GetTrackFromStorage {
    private Context mContext;
    private CallBack mCallBack;
    private List<Track> mTracks;

    public GetTrackFromStorage(Context context, CallBack callBack) {
        mContext = context;
        mCallBack = callBack;
    }

    private List<Track> getSongList() {
        mTracks = new ArrayList<>();
        ContentResolver resolver = mContext.getContentResolver();
        Uri musicUri = android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        Cursor musicCursor = resolver.query(musicUri, null, null, null, null);
        if (musicCursor != null && musicCursor.moveToFirst()) {
            int nameColumn = musicCursor.getColumnIndex
                    (android.provider.MediaStore.Audio.Media.TITLE);
            int idColumn = musicCursor.getColumnIndex
                    (android.provider.MediaStore.Audio.Media._ID);
            int artistColumn = musicCursor.getColumnIndex
                    (android.provider.MediaStore.Audio.Media.ARTIST);
            int durationColume = musicCursor.getColumnIndex
                    (MediaStore.Audio.Media.DURATION);
            do {
                long id = musicCursor.getLong(idColumn);
                String name = musicCursor.getString(nameColumn);
                String artist = musicCursor.getString(artistColumn);
                String path = ContentUris.withAppendedId(
                        android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                        id).toString();
                int duration = musicCursor.getInt(durationColume);
                Track track = new Track(path, artist, name, duration);
                track.setDownloadable(false);
                mTracks.add(track);
            }
            while (musicCursor.moveToNext());
        }
        return mTracks;
    }

    public void getSongLocal() {
        mCallBack.getDataSuccess(getSongList());
    }

    public void getTotalSongLocal() {
        mCallBack.getDataSuccess(getSongList().size());
    }
}
