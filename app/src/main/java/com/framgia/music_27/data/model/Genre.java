package com.framgia.music_27.data.model;

import java.util.List;

public class Genre {

    private String mName;
    private List<Music> mMusics;

    public Genre(String name, List<Music> musics) {
        mName = name;
        mMusics = musics;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public List<Music> getMusics() {
        return mMusics;
    }

    public void setMusics(List<Music> musics) {
        mMusics = musics;
    }
}
