package com.framgia.music_27.screen.library;

import android.content.Context;
import com.framgia.music_27.data.model.Track;
import com.framgia.music_27.data.repository.TrackRepository;
import com.framgia.music_27.data.source.CallBack;
import com.framgia.music_27.screen.local_music.LocalTrackContract;
import java.util.List;

public class LibraryPresent implements LibraryContract.Presenter{
    private LibraryContract.View mView;
    private TrackRepository mTrackRepository;

    public LibraryPresent(TrackRepository trackRepository) {
        mTrackRepository = trackRepository;
    }

    @Override
    public void getTotalLocalMusicLocal() {
        mTrackRepository.getTotalLocalMusic(new CallBack<Integer>() {
            @Override
            public void getDataSuccess(Integer datas) {
                mView.onGetTotalLocalMusicSuccess(datas);
            }

            @Override
            public void getDataFailure(Exception e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void getTrackDownload(String type) {
        mTrackRepository.getDownloadTrack(type, new CallBack<Integer>() {
            @Override
            public void getDataSuccess(Integer datas) {
                mView.onGetTotalDownloadTrackSuccess(datas);
            }

            @Override
            public void getDataFailure(Exception e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void setView(LibraryContract.View view) {
        mView = view;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }
}
