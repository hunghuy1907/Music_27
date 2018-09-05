package com.framgia.music_27.screen.genre;

import com.framgia.music_27.data.model.Genre;
import com.framgia.music_27.screen.base.BasePresenter;

public class GenreContract {
    /**
     * View.
     */
    interface View {
        void onGetTracksByGenreSuccess(Genre genre);

        void hideProgress();
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter<View> {
        void getTracksByGenre(String type);
    }
}
