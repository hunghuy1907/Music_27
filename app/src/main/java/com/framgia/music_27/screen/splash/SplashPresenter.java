package com.framgia.music_27.screen.splash;

import com.framgia.music_27.data.model.Genre;
import com.framgia.music_27.data.repository.TrackRepository;
import com.framgia.music_27.data.source.CallBack;
import java.util.List;

public class SplashPresenter implements SplashContract.Presenter {

    private TrackRepository mTrackRepository;
    private SplashContract.View mView;

    public SplashPresenter(TrackRepository trackRepository) {
        mTrackRepository = trackRepository;
    }

    @Override
    public void setView(SplashContract.View view) {
        mView = view;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void loadTrackByGenre(String type, List datas) {
        mTrackRepository.getTracksByGenre(type, datas, new CallBack<List<Genre>>() {
            @Override
            public void getDataSuccess(List<Genre> datas) {
                mView.sendDataGenre(datas);
            }

            @Override
            public void getDataFailure(Exception e) {

            }
        });

    }
}
