package com.framgia.music_27.screen.local_music;

import com.framgia.music_27.data.model.Track;
import com.framgia.music_27.screen.base.BasePresenter;
import java.util.List;

public class LocalTrackContract {
    /**
     * View.
     */
    interface View {

        void onGetTracksLocalSuccess(List<Track> tracks);
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter<View> {

        void getTracksLocal();
    }
}
