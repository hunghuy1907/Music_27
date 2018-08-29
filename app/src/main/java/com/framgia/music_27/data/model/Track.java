package com.framgia.music_27.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.framgia.music_27.utils.Constants;
import org.json.JSONObject;

public class Track implements Parcelable {

    private int mId;
    private String mTitle;
    private String mUri;
    private String mArtworkUrl;
    private int mDuration;
    private String mGenre;
    private boolean mDownloadable;
    private String mDownloadURL;
    private User mUser;

    protected Track(Parcel in) {
        mId = in.readInt();
        mTitle = in.readString();
        mUri = in.readString();
        mArtworkUrl = in.readString();
        mDuration = in.readInt();
        mGenre = in.readString();
        mDownloadable = in.readByte() != 0;
        mDownloadURL = in.readString();
        mUser = in.readParcelable(User.class.getClassLoader());
    }

    private Track(TrackBuilder trackBuilder) {
        mId = trackBuilder.mId;
        mTitle = trackBuilder.mTitle;
        mUri = trackBuilder.mUri;
        mArtworkUrl = trackBuilder.mArtworkUrl;
        mDuration = trackBuilder.mDuration;
        mGenre = trackBuilder.mGenre;
        mDownloadable = trackBuilder.mDownloadable;
        mDownloadURL = trackBuilder.mDownloadUrl;
        mUser = trackBuilder.mUser;
    }

    public Track(JSONObject jsonObject) throws Exception {
        mId = jsonObject.getInt(Constants.JsonTrackKey.ID);
        mTitle = jsonObject.getString(Constants.JsonTrackKey.TITLE);
        mUri = jsonObject.getString(Constants.JsonTrackKey.URI);
        mArtworkUrl = jsonObject.getString(Constants.JsonTrackKey.ARTWORK_URL);
        mDuration = jsonObject.getInt(Constants.JsonTrackKey.DURATION);
        mGenre = jsonObject.getString(Constants.JsonTrackKey.GENRE);
        mDownloadable = jsonObject.getBoolean(Constants.JsonTrackKey.DOWNLOADABLE);
        if (mDownloadable) {
            mDownloadURL = jsonObject.getString(Constants.JsonTrackKey.DOWNLOAD_URL);
        }
        mUser = new User(jsonObject.getJSONObject(Constants.JsonUserKey.USER_FULLNAME));
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

    public static class TrackBuilder{
        private int mId;
        private String mTitle;
        private String mUri;
        private String mArtworkUrl;
        private int mDuration;
        private String mGenre;
        private boolean mDownloadable;
        private String mDownloadUrl;
        private User mUser;

        public TrackBuilder id (int id) {
            mId = id;
            return this;
        }

        public TrackBuilder user (User user) {
            mUser = user;
            return this;
        }

        public TrackBuilder title (String title) {
            mTitle = title;
            return this;
        }

        public TrackBuilder url (String url) {
            mUri = url;
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

        public Track create() {
            return new Track(this);
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mId);
        dest.writeString(mTitle);
        dest.writeString(mArtworkUrl);
        dest.writeString(mUri);
        dest.writeString(mArtworkUrl);
        dest.writeInt(mDuration);
        dest.writeString(mGenre);
        dest.writeByte((byte) (mDownloadable ? 1 : 0));
        dest.writeString(mDownloadURL);
        dest.writeParcelable(mUser, flags);
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

    public String getUri() {
        return mUri;
    }

    public void setUri(String uri) {
        mUri = uri;
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

    public User getUser() {
        return mUser;
    }

    public void setUser(User user) {
        mUser = user;
    }
}
