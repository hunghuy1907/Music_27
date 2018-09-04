package com.framgia.music_27.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;

public class Genre implements Parcelable {

    private String mName;
    private ArrayList<Track> mTracks;

    public Genre(String name, ArrayList<Track> tracks) {
        mName = name;
        mTracks = tracks;
    }


    private Genre(GenreBuilder genreBuilder) {
        mName = genreBuilder.mName;
        mTracks = genreBuilder.mTracks;
    }

    protected Genre(Parcel in) {
        mName = in.readString();
        mTracks = in.createTypedArrayList(Track.CREATOR);
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

    public ArrayList<Track> getTracks() {
        return mTracks;
    }

    public void setTracks(ArrayList<Track> tracks) {
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

    public static class GenreBuilder {
        private String mName;
        private ArrayList<Track> mTracks;

        public GenreBuilder name(String name) {
            mName = name;
            return this;
        }

        public GenreBuilder tracks(ArrayList<Track> tracks) {
            mTracks = tracks;
            return this;
        }

        public Genre build() {
            return new Genre(this);
        }
    }
}
