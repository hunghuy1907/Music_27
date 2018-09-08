package com.framgia.music_27.service;

import android.support.annotation.IntDef;

import static com.framgia.music_27.service.LoopMode.LOOP_ALL;
import static com.framgia.music_27.service.LoopMode.LOOP_OFF;
import static com.framgia.music_27.service.LoopMode.LOOP_ONE;

@IntDef({LOOP_ONE, LOOP_ALL, LOOP_OFF})
public @interface LoopMode {
    int LOOP_OFF = 0;
    int LOOP_ONE = 1;
    int LOOP_ALL = 2;
}
