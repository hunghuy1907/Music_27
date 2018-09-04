package com.framgia.music_27.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.framgia.music_27.utils.Constants;
import org.json.JSONException;
import org.json.JSONObject;

public class Track implements Parcelable {

    private int mId;
    private String mTitle;
    private String mArtworkUrl;
    private int mDuration;
    private String mGenre;
    private boolean mDownloadable;
    private String mDownloadURL;
    private String mArtist;

    public Track(int id, String title, String artworkUrl, int duration, String genre,
            boolean downloadable, String downloadURL, String artist) {
        mId = id;
        mTitle = title;
        mArtworkUrl = artworkUrl;
        mDuration = duration;
        mGenre = genre;
        mDownloadable = downloadable;
        mDownloadURL = downloadURL;
        mArtist = artist;
    }

    private Track(TrackBuilder trackBuilder) {
        mId = trackBuilder.mId;
        mTitle = trackBuilder.mTitle;
        mArtworkUrl = trackBuilder.mArtworkUrl;
        mDuration = trackBuilder.mDuration;
        mGenre = trackBuilder.mGenre;
        mDownloadable = trackBuilder.mDownloadable;
        mDownloadURL = trackBuilder.mDownloadUrl;
        mArtist = trackBuilder.mArtist;
    }
    public Track(JSONObject jsonObject) throws JSONException {
        mId = jsonObject.getInt(Constants.JsonTrackKey.ID);
        mTitle = jsonObject.getString(Constants.JsonTrackKey.TITLE);
        mArtworkUrl = jsonObject.getString(Constants.JsonTrackKey.ARTWORK_URL);
        mDuration = jsonObject.getInt(Constants.JsonTrackKey.DURATION);
        mGenre = jsonObject.getString(Constants.JsonTrackKey.GENRE);
        mDownloadable = jsonObject.getBoolean(Constants.JsonTrackKey.DOWNLOADABLE);
        if (mDownloadable) {
            mDownloadURL = jsonObject.getString(Constants.JsonTrackKey.DOWNLOAD_URL);
        }
        JSONObject user = jsonObject.getJSONObject(Constants.JsonTrackKey.USER);
        mArtist = user.getString(Constants.JsonUserKey.USER_FULLNAME);
    }

    protected Track(Parcel in) {
        mId = in.readInt();
        mTitle = in.readString();
        mArtworkUrl = in.readString();
        mDuration = in.readInt();
        mGenre = in.readString();
        mDownloadable = in.readByte() != 0;
        mDownloadURL = in.readString();
        mArtist = in.readString();
    }

    public static final Creator<Track> CREATOR = new Creator<Track>() {
        @Override
        public Track createFromParcel(Parcel in) {
            return new Track(in);
        }

        @Override
        public Track[] newArray(int size) {
            return new Track[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mId);
        dest.writeString(mTitle);
        dest.writeString(mArtworkUrl);
        dest.writeInt(mDuration);
        dest.writeString(mGenre);
        dest.writeByte((byte) (mDownloadable ? 1 : 0));
        dest.writeString(mDownloadURL);
        dest.writeString(mArtist);
    }

    public static class TrackBuilder{
        private int mId;
        private String mTitle;
        private String mArtworkUrl;
        private int mDuration;
        private String mGenre;
        private boolean mDownloadable;
        private String mDownloadUrl;
        private String mArtist;


        public TrackBuilder id (int id) {
            mId = id;
            return this;
        }

        public TrackBuilder title (String title) {
            mTitle = title;
            return this;
        }

        public TrackBuilder artworkUrl (String artworkUrl) {
            mArtworkUrl = artworkUrl;
            return this;
        }

        public TrackBuilder duration (int duration) {
            mDuration = duration;
            return this;
        }

        public TrackBuilder genre (String genre) {
            mGenre = genre;
            return this;
        }

        public TrackBuilder downloadable (boolean downloadable) {
            mDownloadable = downloadable;
            return this;
        }

        public TrackBuilder downloadUrl (String downloadUrl) {
            mDownloadUrl = downloadUrl;
            return this;
        }

        public TrackBuilder artist (String artist) {
            mArtist = artist;
            return this;
        }

        public Track create() {
            return new Track(this);
        }
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getArtworkUrl() {
        return mArtworkUrl;
    }

    public void setArtworkUrl(String artworkUrl) {
        mArtworkUrl = artworkUrl;
    }

    public int getDuration() {
        return mDuration;
    }

    public void setDuration(int duration) {
        mDuration = duration;
    }

    public String getGenre() {
        return mGenre;
    }

    public void setGenre(String genre) {
        mGenre = genre;
    }

    public boolean isDownloadable() {
        return mDownloadable;
    }

    public void setDownloadable(boolean downloadable) {
        mDownloadable = downloadable;
    }

    public String getDownloadURL() {
        return mDownloadURL;
    }

    public void setDownloadURL(String downloadURL) {
        mDownloadURL = downloadURL;
    }

    public String getArtist() {
        return mArtist;
    }

    public void setArtist(String artist) {
        mArtist = artist;
    }
}
