package com.framgia.music_27.screen.player;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import com.framgia.music_27.R;
import com.framgia.music_27.screen.base.BaseActivity;
import com.framgia.music_27.service.MusicService;

public class PlayerActivity extends BaseActivity implements View.OnClickListener,
        SeekBar.OnSeekBarChangeListener, OnMediaPlayerChangeListener {
    private Button mButtonPlay;
    private Button mButtonNext;
    private Button mButtonPrevios;
    private Button mButtonPreDownload;
    private Button mButtonPreShuffle;
    private Button mButtonloop;
    private Button mButtonListMusic;
    private Button mButtonAddList;
    private Button mButtonLike;
    private MusicService mMusicService;
    private boolean mBound;
    private PlayerMusicManger mPlayerMusicManger;
    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MusicService.MusicBinder boundService = (MusicService.MusicBinder) service;
            mMusicService = boundService.getService();
            mMusicService.play(mMusicService.getUrlCurrent());
            mPlayerMusicManger = mMusicService.getPlayerMusic();
            mPlayerMusicManger.setOnMediaPlayerChangeListener(PlayerActivity.this);
            mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mBound = false;
        }
    };

    public static Intent getIntentPlayerActivity(Context context) {
        Intent intent = new Intent(context, PlayerActivity.class);
        return intent;
    }

    @Override
    protected int getLayoutResources() {
        return R.layout.activity_player;
    }

    @Override
    protected void initComponents() {
        mButtonPlay = findViewById(R.id.button_play);
        mButtonPrevios = findViewById(R.id.button_previous);
        mButtonNext = findViewById(R.id.button_next);
        mButtonPlay.setOnClickListener(this);
        mButtonNext.setOnClickListener(this);
        mButtonPrevios.setOnClickListener(this);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(this, MusicService.class);
        getApplication().bindService(intent, mServiceConnection, BIND_AUTO_CREATE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_play:
                mPlayerMusicManger.pause();
                break;

            case R.id.button_next:
                mPlayerMusicManger.next();
                break;

            case R.id.button_previous:
                mPlayerMusicManger.previous();
                break;

            default:
                break;
        }
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onMediaStateChanged(boolean isPlaying) {
        if (isPlaying) {
            mButtonPlay.setBackgroundResource(R.drawable.pause);
        } else {
            mButtonPlay.setBackgroundResource(R.drawable.ic_play_button);
        }
    }
}
