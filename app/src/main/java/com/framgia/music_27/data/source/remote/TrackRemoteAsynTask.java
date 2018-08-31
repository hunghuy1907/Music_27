package com.framgia.music_27.data.source.remote;

import android.os.AsyncTask;
import com.framgia.music_27.data.model.Genre;
import com.framgia.music_27.data.model.Track;
import com.framgia.music_27.data.source.CallBack;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;

public class TrackRemoteAsynTask extends AsyncTask<String, Void, List<Track>> {
    private CallBack<List<Genre>> mCallBack;
    private Exception mException;
    private String mType;
    private List<Genre> mGenres;

    public TrackRemoteAsynTask(CallBack callBack, String type, List<Genre> genres) {
        mCallBack = callBack;
        mType = type;
        mGenres = genres;
    }

    @Override
    protected List<Track> doInBackground(String... strings) {
        try {
            String json = FetchDataFromAPI.getJSONStringFromURL(strings[0]);
            return FetchDataFromAPI.getTrackFromJson(json);
        } catch (IOException e) {
            e.printStackTrace();
            mException = e;
        } catch (JSONException e) {
            e.printStackTrace();
            mException = e;
        } catch (Exception e) {
            e.printStackTrace();
            mException = e;
        }
        return null;
    }

    @Override
    protected void onPostExecute(List<Track> tracks) {
        super.onPostExecute(tracks);
        if (mCallBack == null) {
            return;
        }
        if (mException == null){
            Genre genre = new Genre(mType, tracks);
            mGenres.add(genre);
            mCallBack.getDataSuccess(mGenres);
        } else {
            mCallBack.getDataFailure(mException);
        }
    }
}
