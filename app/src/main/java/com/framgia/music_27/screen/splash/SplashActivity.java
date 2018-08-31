package com.framgia.music_27.screen.splash;

import android.os.Bundle;
import com.framgia.music_27.R;
import com.framgia.music_27.data.model.Genre;
import com.framgia.music_27.data.repository.TrackRepository;
import com.framgia.music_27.data.source.remote.TrackRemoteDataResource;
import com.framgia.music_27.screen.base.BaseActivity;
import com.framgia.music_27.screen.home.HomeActivity;
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
        mPresenter = new SplashPresenter(TrackRepository
                .getInstance(TrackRemoteDataResource.getInstance()));
        loadDataByGenre();
    }

    @Override
    public void sendDataGenre(List<Genre> genres) {
        startActivity(HomeActivity.getProfileIntent(this, genres));
    }

    public void loadDataByGenre() {
        List<Genre> genres = new ArrayList<>();
        mPresenter.loadTrackByGenre(String.valueOf(R.string.title_all_music), genres);
        mPresenter.loadTrackByGenre(String.valueOf(R.string.title_all_audio), genres);
        mPresenter.loadTrackByGenre(String.valueOf(R.string.title_ambiant), genres);
        mPresenter.loadTrackByGenre(String.valueOf(R.string.title_alternative_rock), genres);
        mPresenter.loadTrackByGenre(String.valueOf(R.string.title_classical), genres);
        mPresenter.loadTrackByGenre(String.valueOf(R.string.title_country), genres);
    }
}
