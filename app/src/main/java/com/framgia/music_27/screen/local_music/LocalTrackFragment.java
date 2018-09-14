package com.framgia.music_27.screen.local_music;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.framgia.music_27.R;
import com.framgia.music_27.data.model.Track;
import com.framgia.music_27.data.repository.TrackRepository;
import com.framgia.music_27.data.source.local.TrackLocalDataSource;
import com.framgia.music_27.data.source.remote.TrackRemoteDataResource;
import com.framgia.music_27.screen.base.BaseFragment;
import com.framgia.music_27.screen.player.PlayerActivity;
import com.framgia.music_27.service.MusicService;
import java.util.ArrayList;
import java.util.List;

import static com.framgia.music_27.utils.Constants.Player.TYPE_LOCAL;

public class LocalTrackFragment extends BaseFragment
        implements LocalTrackContract.View, OnClickTrackLocal {

    public static final String TAG = "LocalTrackFragment";
    private static LocalTrackFragment sInstance;

    private LocalTrackContract.Presenter mPresenter;
    private RecyclerView mRecyclerTrack;
    private List<Track> mTracks;

    public static LocalTrackFragment getInstance() {
        return new LocalTrackFragment();
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_local_track;
    }

    @Override
    protected void initComponents() {
        mRecyclerTrack = getActivity().findViewById(R.id.recycle_song_local);
    }

    @Override
    protected void initData(Bundle saveInstanceState) {
        mTracks = new ArrayList<>();
        mPresenter = new LocalTrackPresent(TrackRepository
                .getInstance(TrackRemoteDataResource.getInstance(), TrackLocalDataSource.getsInstance(getActivity())));
        mPresenter.setView(this);
        mPresenter.getTracksLocal();
        initRecycleView();
    }

    private void initRecycleView() {
        LocalTrackAdapter genreAdapter = new LocalTrackAdapter(getActivity(), mTracks, this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false);
        mRecyclerTrack.setLayoutManager(linearLayoutManager);
        mRecyclerTrack.setAdapter(genreAdapter);
    }

    @Override
    public void onGetTracksLocalSuccess(List<Track> tracks) {
        mTracks = tracks;
    }

    @Override
    public void clickTrackLocal(int position) {
        getActivity().startService(
                MusicService.getIntentService(getActivity(), mTracks, position, TYPE_LOCAL));
        startActivity(PlayerActivity.getIntentPlayerActivity(getActivity()));
    }
}
