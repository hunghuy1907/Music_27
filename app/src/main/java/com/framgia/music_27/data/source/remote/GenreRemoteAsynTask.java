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

public class GenreRemoteAsynTask extends AsyncTask<String, Void, List<Track>> {
    private CallBack mCallBack;
    private Exception mException;
    private String mTitle;

    public GenreRemoteAsynTask(CallBack callBack, String title) {
        mCallBack = callBack;
        mTitle = title;
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
            mCallBack.getDataSuccess(new Genre(mTitle
                    .replace(Constants.Genre.MINUS, Constants.Genre.SPACE)
                    .toUpperCase(), (ArrayList<Track>) tracks));
        } else {
            mCallBack.getDataFailure(mException);
        }
    }
}
