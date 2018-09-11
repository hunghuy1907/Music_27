package com.framgia.music_27.service;

import com.framgia.music_27.data.model.Track;
import com.framgia.music_27.service.LoopMode;
import com.framgia.music_27.service.ShuffleMode;

public interface OnMediaPlayerChangeListener {
    void onMediaStateChanged(boolean isPlaying);

    void onLoopStateChange(@LoopMode int state);

    void onShuffleStateChange(@ShuffleMode int state);

    void onTrackChange(Track track);

    void onDownloadStateChange(@ShuffleMode int state);
}
