package com.framgia.music_27.data.source.remote;

import com.framgia.music_27.data.model.Track;
import com.framgia.music_27.utils.Constants;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class FetchDataFromAPI {

    public static String getJSONStringFromURL(String urlString) throws IOException {
        HttpURLConnection urlConnection;
        URL url = new URL(urlString);
        urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod(Constants.JsonTrackKey.REQUEST_METHOD_GET);
        urlConnection.setReadTimeout(Constants.JsonTrackKey.READ_TIMEOUT);
        urlConnection.setConnectTimeout(Constants.JsonTrackKey.CONNECTION_TIMEOUT);
        urlConnection.setDoOutput(true);
        urlConnection.connect();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()));
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            stringBuilder.append(line + "\n");
        }
        bufferedReader.close();
        String jsonString = stringBuilder.toString();
        urlConnection.disconnect();
        return jsonString;
    }

    public static List<Track> getTrackFromJson(String json) throws JSONException {
        List<Track> tracks = new ArrayList<>();
        JSONObject root = new JSONObject(json);
        JSONArray jsonArray = root.getJSONArray(Constants.JsonKey.RESULT);
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i)
                    .getJSONObject(Constants.JsonKey.JSON_TRACK);
            Track track = new Track(jsonObject);
            tracks.add(track);
        }
        return tracks;
    }
}
