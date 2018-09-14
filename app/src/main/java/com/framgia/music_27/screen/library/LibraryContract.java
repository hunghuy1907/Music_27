package com.framgia.music_27.screen.library;

import android.content.Context;
import com.framgia.music_27.screen.base.BasePresenter;

public class LibraryContract {
    /**
     * View.
     */
    interface View {

        void onGetTotalLocalMusicSuccess(Integer integer);

        void onGetTotalDownloadTrackSuccess(Integer integer);
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter<View> {

        void getTotalLocalMusicLocal();

        void getTrackDownload(String type);
    }
}
