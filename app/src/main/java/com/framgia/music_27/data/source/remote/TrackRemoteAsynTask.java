package com.framgia.music_27.data.source.remote;

import android.os.AsyncTask;
import com.framgia.music_27.data.model.Genre;
import com.framgia.music_27.data.model.Track;
import com.framgia.music_27.data.source.CallBack;
import com.framgia.music_27.utils.Constants;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;

public class TrackRemoteAsynTask extends AsyncTask<String, Void, List<Track>> {
    private CallBack mCallBack;
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
        List<Track> tracks = new ArrayList<>();
        try {
            String json = FetchDataFromAPI.getJSONStringFromURL(strings[0]);
            tracks = FetchDataFromAPI.getTrackFromJson(json);
        } catch (IOException e) {
            e.printStackTrace();
            mException = e;
        } catch (JSONException e) {
            e.printStackTrace();
            mException = e;
        }
        return tracks;
    }

    @Override
    protected void onPostExecute(List<Track> tracks) {
        super.onPostExecute(tracks);
        if (mCallBack == null) {
            return;
        }
        if (mException == null){
            Genre genre = new Genre(mType.replace(Constants.Genre.MINUS, Constants.Genre.SPACE)
                    .toUpperCase(), (ArrayList<Track>) tracks);
            mGenres.add(genre);
            mCallBack.getDataSuccess(mGenres);
        } else {
            mCallBack.getDataFailure(mException);
        }
    }
}
