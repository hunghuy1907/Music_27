package com.framgia.music_27.screen.splash;

import android.os.Bundle;
import com.framgia.music_27.R;
import com.framgia.music_27.data.model.Genre;
import com.framgia.music_27.data.repository.TrackRepository;
import com.framgia.music_27.data.source.remote.TrackRemoteDataResource;
import com.framgia.music_27.screen.base.BaseActivity;
import com.framgia.music_27.screen.home.HomeActivity;
import com.framgia.music_27.utils.Constants;
import java.util.ArrayList;
import java.util.List;

public class SplashActivity extends BaseActivity implements SplashContract.View {

    private SplashContract.Presenter mPresenter;

    @Override
    protected int getLayoutResources() {
        return R.layout.activity_soundclound;
    }

    @Override
    protected void initComponents() {

    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        mPresenter = new SplashPresenter(
                TrackRepository.getInstance(TrackRemoteDataResource.getInstance()));
        mPresenter.setView(this);
        loadDataByGenre();
    }

    @Override
    public void sendDataGenre(List<Genre> genres) {
        startActivity(HomeActivity.getProfileIntent(this, new ArrayList<>(genres)));
        finish();
    }

    public void loadDataByGenre() {
        List<Genre> genres = new ArrayList<>();
        mPresenter.loadTrackByGenre(Constants.Genre.ALL_MUSIC, genres);
        mPresenter.loadTrackByGenre(Constants.Genre.ALL_AUDIO, genres);
        mPresenter.loadTrackByGenre(Constants.Genre.ALTERNATIVE_ROCK, genres);
        mPresenter.loadTrackByGenre(Constants.Genre.AMBIENT, genres);
        mPresenter.loadTrackByGenre(Constants.Genre.COUNTRY, genres);
        mPresenter.loadTrackByGenre(Constants.Genre.CLASSICAL, genres);
    }
}
