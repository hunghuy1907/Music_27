package com.framgia.music_27.screen.local_music;

import com.framgia.music_27.data.model.Track;
import com.framgia.music_27.data.repository.TrackRepository;
import com.framgia.music_27.data.source.CallBack;
import java.util.List;

public class LocalTrackPresent implements LocalTrackContract.Presenter {
    private LocalTrackContract.View mView;
    private TrackRepository mTrackRepository;

    public LocalTrackPresent(TrackRepository trackRepository) {
        mTrackRepository = trackRepository;
    }

    @Override
    public void getTracksLocal() {
        mTrackRepository.getTrack(new CallBack<List<Track>>() {
            @Override
            public void getDataSuccess(List<Track> datas) {
                mView.onGetTracksLocalSuccess(datas);
            }

            @Override
            public void getDataFailure(Exception e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void setView(LocalTrackContract.View view) {
        mView = view;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }
}
