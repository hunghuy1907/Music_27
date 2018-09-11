package com.framgia.music_27.service;

import android.support.annotation.IntDef;

import static com.framgia.music_27.service.ShuffleMode.SHUFFLE_OFF;
import static com.framgia.music_27.service.ShuffleMode.SHUFFLE_ON;

@IntDef({SHUFFLE_ON, SHUFFLE_OFF})
public @interface ShuffleMode {
    int SHUFFLE_ON = 1;
    int SHUFFLE_OFF = 0;
}
