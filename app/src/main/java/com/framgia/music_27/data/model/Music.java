package com.framgia.music_27.data.model;

public class Music {

    private String mSong;
    private String mSinger;
    private int mImagePath;

    public Music(String song, String singer, int imagePath) {
        mSong = song;
        mSinger = singer;
        mImagePath = imagePath;
    }

    public String getSong() {
        return mSong;
    }

    public void setSong(String song) {
        mSong = song;
    }

    public String getSinger() {
        return mSinger;
    }

    public void setSinger(String singer) {
        mSinger = singer;
    }

    public int getImagePath() {
        return mImagePath;
    }

    public void setImagePath(int imagePath) {
        mImagePath = imagePath;
    }
}
