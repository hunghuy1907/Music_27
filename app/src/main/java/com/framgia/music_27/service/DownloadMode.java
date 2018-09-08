package com.framgia.music_27.service;

import android.support.annotation.IntDef;

import static com.framgia.music_27.service.DownloadMode.DOWNLOADABLE;
import static com.framgia.music_27.service.DownloadMode.DOWNLOADED;

@IntDef({DOWNLOADABLE, DOWNLOADED})
public @interface DownloadMode {
    int DOWNLOADABLE = 0;
    int DOWNLOADED = 1;
}
