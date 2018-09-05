package com.framgia.music_27.screen.genre;

import com.framgia.music_27.data.model.Genre;
import com.framgia.music_27.data.repository.TrackRepository;
import com.framgia.music_27.data.source.CallBack;

public class GenrePresent implements GenreContract.Presenter {
    private GenreContract.View mView;
    private TrackRepository mTrackRepository;

    public GenrePresent(TrackRepository trackRepository) {
        mTrackRepository = trackRepository;
    }

    @Override
    public void getTracksByGenre(String type) {
        mTrackRepository.getGenres(type, new CallBack<Genre>() {

            @Override
            public void getDataSuccess(Genre genre) {
                mView.onGetTracksByGenreSuccess(genre);
                mView.hideProgress();
            }

            @Override
            public void getDataFailure(Exception e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void setView(GenreContract.View view) {
        mView = view;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }
}
