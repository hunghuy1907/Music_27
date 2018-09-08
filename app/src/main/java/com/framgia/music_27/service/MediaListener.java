package com.framgia.music_27.service;

import com.framgia.music_27.data.model.Track;

public interface MediaListener {
    void start();

    boolean isPlaying();

    void play(String url);

    void stop();

    void pause();

    void next();

    void release();

    void previous();

    void seek(int duration);

    void loop();

    void shuffle();

    int getCurrentDuration();

    Track getCurrentTrack();

    int getDuration();

    void download();
}
