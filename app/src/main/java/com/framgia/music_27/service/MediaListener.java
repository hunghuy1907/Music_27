package com.framgia.music_27.service;

import android.widget.Button;

public interface MediaListener {
    void play(String url);

    void stop();

    void pause();

    void next();

    void release();

    void previous();

    void seek(int duration);

    void loop();

    void shuffle();
}
