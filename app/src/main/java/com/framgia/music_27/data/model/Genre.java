package com.framgia.music_27.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;

public class Genre implements Parcelable{

    private String mName;
    private List<Track> mTracks;

    public Genre(String name, List<Track> tracks) {
        mName = name;
        mTracks = tracks;
    }

    protected Genre(Parcel in) {
        mName = in.readString();
        mTracks = in.createTypedArrayList(Track.CREATOR);
    }

    private Genre(GenreBuider genreBuider) {
        mName = genreBuider.mName;
        mTracks = genreBuider.mTracks;
    }

    public static final Creator<Genre> CREATOR = new Creator<Genre>() {
        @Override
        public Genre createFromParcel(Parcel in) {
            return new Genre(in);
        }

        @Override
        public Genre[] newArray(int size) {
            return new Genre[size];
        }
    };

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public List<Track> getTracks() {
        return mTracks;
    }

    public void setTracks(List<Track> tracks) {
        mTracks = tracks;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mName);
        dest.writeTypedList(mTracks);
    }

    public class GenreBuider {
        private String mName;
        private List<Track> mTracks;

        public GenreBuider name(String name) {
            mName = name;
            return this;
        }

        public GenreBuider tracks(List<Track> tracks) {
            mTracks = tracks;
            return this;
        }

        public Genre build() {
            return new Genre(this);
        }
    }
}
