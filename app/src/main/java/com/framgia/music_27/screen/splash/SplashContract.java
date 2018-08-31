package com.framgia.music_27.screen.splash;

import com.framgia.music_27.data.model.Genre;
import com.framgia.music_27.screen.base.BasePresenter;
import java.util.List;

public class SplashContract {
    /**
     * View.
     */
    interface View {
        void sendDataGenre(List<Genre> genres);
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter<SplashContract.View> {
        void loadTrackByGenre(String type, List datas);
    }
}
