package com.framgia.music_27.screen.player;

import android.app.DownloadManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.framgia.music_27.R;
import com.framgia.music_27.data.model.Track;
import com.framgia.music_27.data.source.remote.TrackRemoteDataResource;
import com.framgia.music_27.screen.base.BaseActivity;
import com.framgia.music_27.service.MediaListener;
import com.framgia.music_27.service.MusicService;
import com.framgia.music_27.service.OnMediaPlayerChangeListener;
import com.framgia.music_27.utils.Constants;
import com.framgia.music_27.utils.StringUtils;
import java.io.File;

import static com.framgia.music_27.service.DownloadMode.DOWNLOADABLE;
import static com.framgia.music_27.service.DownloadMode.DOWNLOADED;
import static com.framgia.music_27.service.LoopMode.LOOP_ALL;
import static com.framgia.music_27.service.LoopMode.LOOP_OFF;
import static com.framgia.music_27.service.LoopMode.LOOP_ONE;
import static com.framgia.music_27.service.ShuffleMode.SHUFFLE_OFF;
import static com.framgia.music_27.service.ShuffleMode.SHUFFLE_ON;
import static com.framgia.music_27.utils.Constants.Player.FILE_DIR;
import static com.framgia.music_27.utils.Constants.Player.MP3_FORMAT;

public class PlayerActivity extends BaseActivity
        implements View.OnClickListener, SeekBar.OnSeekBarChangeListener,
        OnMediaPlayerChangeListener {
    private Button mButtonPlay;
    private Button mButtonNext;
    private Button mButtonPrevios;
    private Button mButtonDownload;
    private Button mButtonShuffle;
    private Button mButtonLoop;
    private Button mButtonListMusic;
    private Button mButtonAddList;
    private Button mButtonLike;
    private SeekBar mSeekBar;
    private ImageView mImagePlayer;
    private TextView mTextDuration;
    private TextView mTextCurrentDuration;
    private TextView mTextSongPlayer;
    private TextView mTextArtistPlayer;
    private MusicService mMusicService;
    private Handler mHandler;
    private MediaListener mMediaListener;
    private Runnable mRunnable;
    private boolean mIsBound;
    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MusicService.MusicBinder boundService = (MusicService.MusicBinder) service;
            mMusicService = boundService.getService();
            mMusicService.setOnMediaPlayerChangeListener(PlayerActivity.this);
            mMediaListener = mMusicService.getMediaListener();
            setDataTrack(mMediaListener.getCurrentTrack());
            mHandler.postDelayed(mRunnable, Constants.Player.DELAY);
            mIsBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mIsBound = false;
        }
    };

    public static Intent getIntentPlayerActivity(Context context) {
        Intent intent = new Intent(context, PlayerActivity.class);
        return intent;
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(this, MusicService.class);
        getApplication().bindService(intent, mServiceConnection, BIND_AUTO_CREATE);
    }

    @Override
    protected int getLayoutResources() {
        return R.layout.activity_player;
    }

    @Override
    protected void initComponents() {
        mImagePlayer = findViewById(R.id.image_music_player);
        mTextSongPlayer = findViewById(R.id.text_name_song_player);
        mTextArtistPlayer = findViewById(R.id.text_artist_player);
        mSeekBar = findViewById(R.id.seekBar_player);
        mTextCurrentDuration = findViewById(R.id.text_current_time);
        mTextDuration = findViewById(R.id.text_time_duration);
        mButtonPlay = findViewById(R.id.button_play);
        mButtonPrevios = findViewById(R.id.button_previous);
        mButtonNext = findViewById(R.id.button_next);
        mButtonAddList = findViewById(R.id.button_add_list);
        mButtonLike = findViewById(R.id.button_like);
        mButtonListMusic = findViewById(R.id.button_list_playing);
        mButtonLoop = findViewById(R.id.button_loop);
        mButtonShuffle = findViewById(R.id.button_shuffle);
        mButtonDownload = findViewById(R.id.button_download);
        setEventListener();
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    public void setEventListener() {
        mButtonPlay.setOnClickListener(this);
        mButtonPrevios.setOnClickListener(this);
        mButtonNext.setOnClickListener(this);
        mButtonAddList.setOnClickListener(this);
        mButtonLike.setOnClickListener(this);
        mButtonListMusic.setOnClickListener(this);
        mButtonLoop.setOnClickListener(this);
        mButtonShuffle.setOnClickListener(this);
        mButtonDownload.setOnClickListener(this);
        mSeekBar.setOnSeekBarChangeListener(this);
    }

    public void updateSeekbar() {
        mHandler = new Handler();
        mRunnable = new Runnable() {
            @Override
            public void run() {
                int currentDuration = mMediaListener.getCurrentDuration();
                mTextCurrentDuration.setText(StringUtils.convertMinisecond(currentDuration));
                mSeekBar.setProgress(currentDuration);
                rotateImage(mImagePlayer, true);
                mHandler.postDelayed(mRunnable, Constants.Player.DELAY);
            }
        };
    }

    public void setDataTrack(Track track) {
        mTextSongPlayer.setText(track.getTitle().toString());
        mTextArtistPlayer.setText(track.getArtist().toString());
        if (track.getDuration() != 0) {
            mTextDuration.setText(StringUtils.convertMinisecond(track.getDuration()));
            mSeekBar.setMax(track.getDuration());
        } else {
            mTextDuration.setText(StringUtils.convertMinisecond(mMediaListener.getDuration()));
            mSeekBar.setMax(mMediaListener.getDuration());
        }

        Glide.with(this)
                .load(track.getArtworkUrl())
                .apply(new RequestOptions().placeholder(R.drawable.ic_icon_player)
                        .error(R.drawable.icon_player_error))
                .into(mImagePlayer);
        updateSeekbar();
    }

    public static void rotateImage(ImageView imageView, boolean isRotate) {
        RotateAnimation rotateAnimation =
                new RotateAnimation(0f, 359f, Animation.RELATIVE_TO_SELF, 0.5f,
                        Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setInterpolator(new LinearInterpolator());
        rotateAnimation.setDuration(10000);

        if (isRotate) {
            if (imageView.getAnimation() == null) {
                imageView.startAnimation(rotateAnimation);
            }
        } else {
            imageView.setAnimation(null);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_play:
                mMediaListener.pause();
                break;
            case R.id.button_next:
                mMediaListener.next();
                break;
            case R.id.button_previous:
                mMediaListener.previous();
                break;
            case R.id.button_shuffle:
                mMediaListener.shuffle();
                break;
            case R.id.button_loop:
                mMediaListener.loop();
                break;
            case R.id.button_download:
                mMediaListener.download();
                downloadSong();
                break;
            default:
                break;
        }
    }

    public void downloadSong() {
        DownloadManager downloadManager =
                (DownloadManager) this.getSystemService(Context.DOWNLOAD_SERVICE);
        Uri uriStream = Uri.parse(
                TrackRemoteDataResource.getStreamUrl(mMediaListener.getCurrentTrack().getId()));
        File newFolder = new File(FILE_DIR + Constants.Player.DES_FILE_DOWNLOAD);
        if (!newFolder.exists()) {
            newFolder.mkdir();
        }
        String stringDir = FILE_DIR
                + Constants.Player.DES_FILE_DOWNLOAD
                + "/"
                + mMediaListener.getCurrentTrack().getTitle()
                + MP3_FORMAT;
        DownloadManager.Request request = new DownloadManager.Request(uriStream);
        request.setTitle(
                getString(R.string.text_download) + mMediaListener.getCurrentTrack().getTitle());
        request.setDescription(getString(R.string.text_dowloading));
        request.setNotificationVisibility(
                DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationUri(Uri.parse(stringDir));
        assert downloadManager != null;
        downloadManager.enqueue(request);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        mMediaListener.seek(seekBar.getProgress());
    }

    @Override
    public void onMediaStateChanged(boolean isPlaying) {
        if (isPlaying) {
            mButtonPlay.setBackgroundResource(R.drawable.icon_pause_mini);
        } else {
            mButtonPlay.setBackgroundResource(R.drawable.icon_play_mini);
        }
    }

    @Override
    public void onLoopStateChange(int state) {
        switch (state) {
            case LOOP_OFF:
                mButtonLoop.setBackgroundResource(R.drawable.loop);
                break;
            case LOOP_ONE:
                mButtonLoop.setBackgroundResource(R.drawable.icon_all_loop);
                break;
            case LOOP_ALL:
                mButtonLoop.setBackgroundResource(R.drawable.icon_one_loop);
                break;
            default:
                break;
        }
    }

    @Override
    public void onShuffleStateChange(int state) {
        switch (state) {
            case SHUFFLE_ON:
                mButtonShuffle.setBackgroundResource(R.drawable.icon_shuffle_active);
                break;
            case SHUFFLE_OFF:
                mButtonShuffle.setBackgroundResource(R.drawable.icon_shuffle);
                break;
            default:
                break;
        }
    }

    @Override
    public void onDownloadStateChange(int state) {
        switch (state) {
            case DOWNLOADED:
                mButtonDownload.setBackgroundResource(R.drawable.downloaded);
                break;
            case DOWNLOADABLE:
                mButtonDownload.setBackgroundResource(R.drawable.download);
                break;
            default:
                break;
        }
    }

    @Override
    public void onTrackChange(Track track) {
        setDataTrack(track);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (!mIsBound) {
            unbindService(mServiceConnection);
            mIsBound = false;
        }
    }

    @Override
    protected void onDestroy() {
        mHandler.removeCallbacks(mRunnable);
        super.onDestroy();
    }
}

