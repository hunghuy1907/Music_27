package com.framgia.music_27.screen.genre;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.framgia.music_27.R;
import com.framgia.music_27.data.model.Genre;
import com.framgia.music_27.data.model.Track;
import com.framgia.music_27.data.repository.TrackRepository;
import com.framgia.music_27.data.source.remote.TrackRemoteDataResource;
import com.framgia.music_27.screen.base.BaseActivity;
import com.framgia.music_27.utils.Constants;
import java.util.List;

public class GenreActivity extends BaseActivity implements GenreContract.View, OnClickTrack {
    public static final String EXTRA_GENRE = "com.framgia.music_27.screen.genre_EXTRA_GENRE";
    private GenreContract.Presenter mPresenter;
    private RecyclerView mRecyclerTrack;
    private ProgressBar mProgressBar;
    private TextView mTextTitleGenre;

    @Override
    protected int getLayoutResources() {
        return R.layout.activity_genre;
    }

    @Override
    protected void initComponents() {
        mRecyclerTrack = findViewById(R.id.recycle_song);
        mProgressBar = findViewById(R.id.progress_genre);
        mTextTitleGenre = findViewById(R.id.text_title_genre);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        mPresenter = new GenrePresent(TrackRepository
                .getInstance(TrackRemoteDataResource.getInstance()));
        mPresenter.setView(this);
        loadData();
    }

    public static Intent getIntentProfile(Context context, String genre) {
        Intent intent = new Intent(context, GenreActivity.class);
        intent.putExtra(EXTRA_GENRE, genre);
        return intent;
    }

    private void initRecycleView(List<Track> tracks) {
        GenreAdapter genreAdapter = new GenreAdapter(this, tracks, this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);
        mRecyclerTrack.setLayoutManager(linearLayoutManager);
        mRecyclerTrack.setAdapter(genreAdapter);
    }

    @Override
    public void onGetTracksByGenreSuccess(Genre genre) {
        initRecycleView(genre.getTracks());
        mTextTitleGenre.setText(genre.getName());
    }

    @Override
    public void hideProgress() {
        mProgressBar.setVisibility(View.GONE);
    }

    public String getGenreName() {
        String genre = getIntent().getStringExtra(EXTRA_GENRE)
                .replace(Constants.Genre.SPACE, Constants.Genre.MINUS).toLowerCase();
        return genre;
    }

    public void loadData() {
        mPresenter.getTracksByGenre(getGenreName());
    }

    @Override
    public void clickButtonMore(int position) {

    }
}
