package com.framgia.music_27.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import com.framgia.music_27.data.model.Track;
import com.framgia.music_27.data.source.remote.TrackRemoteDataResource;
import com.framgia.music_27.utils.Constants;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import static com.framgia.music_27.service.DownloadMode.DOWNLOADABLE;
import static com.framgia.music_27.service.LoopMode.LOOP_ALL;
import static com.framgia.music_27.service.LoopMode.LOOP_OFF;
import static com.framgia.music_27.service.LoopMode.LOOP_ONE;
import static com.framgia.music_27.service.ShuffleMode.SHUFFLE_OFF;
import static com.framgia.music_27.service.ShuffleMode.SHUFFLE_ON;
import static com.framgia.music_27.utils.Constants.Player.TYPE_REMOTE;

public class MusicService extends Service
        implements MediaListener, MediaPlayer.OnPreparedListener, MediaPlayer.OnCompletionListener {
    private static final String EXTRA_TRACK = "EXTRA_TRACK";
    private static final String EXTRA_TRACK_POSITION = "EXTRA_TRACK_POSITION";
    private static final String EXTRA_TRACK_TYPE = "EXTRA_TRACK_TYPE";
    public static final String NOTIFY_PLAY = "com.framgia.music_27_NOTIFY_PLAY";
    public static final String NOTIFY_NEXT = "com.framgia.music_27_NOTIFY_NEXT";
    public static final String NOTIFY_PREVIOS = "com.framgia.music_27_NOTIFY_PREVIOUS";
    public static final String ACTION_STATE_MEDIA = "com.framgia.music_27_ACTION_STATE_MEDIA";
    public static final String EXTRA_STATE_MEDIA = "com.framgia.music_27_EXTRA_STATE_MEDIA";

    private MediaPlayer mMediaPlayer;
    private List<Track> mTracks;
    private int mPosition;
    private int mTypeTrack;
    private OnMediaPlayerChangeListener mOnMediaPlayerChangeListener;
    private int mTypeShuffle;
    private int mTypeLoop;
    private int mTypeDownload;
    private Intent mIntentBroadcast;
    private Random mRandom;
    private Context mContext;

    public static Intent getIntentService(Context context, List<Track> tracks, int position,
            int type) {
        Intent intent = new Intent(context, MusicService.class);
        intent.putParcelableArrayListExtra(EXTRA_TRACK, (ArrayList<? extends Parcelable>) tracks);
        intent.putExtra(EXTRA_TRACK_POSITION, position);
        intent.putExtra(EXTRA_TRACK_TYPE, type);
        return intent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        mRandom = new Random();
        mIntentBroadcast = new Intent(ACTION_STATE_MEDIA);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mPosition = intent.getIntExtra(EXTRA_TRACK_POSITION, 0);
        mTypeTrack = intent.getIntExtra(EXTRA_TRACK_TYPE, 0);
        mTracks = intent.getParcelableArrayListExtra(EXTRA_TRACK);
        play(getUrlCurrent());
        return START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new MusicBinder();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        release();
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        release();
        super.onDestroy();
    }

    public MediaListener getMediaListener() {
        return this;
    }

    public String getUrlCurrent() {
        destroyMusic();
        if (mTypeTrack == TYPE_REMOTE) {
            return TrackRemoteDataResource.getStreamUrl(mTracks.get(mPosition).getId());
        } else {
            return mTracks.get(mPosition).getUrlLocal();
        }
    }

    public void destroyMusic() {
        if (mMediaPlayer != null) {
            mMediaPlayer.stop();
            mMediaPlayer.reset();
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
    }

    @Override
    public void start() {
        mMediaPlayer.start();
        mIntentBroadcast.putExtra(EXTRA_STATE_MEDIA, true);
        sendBroadcast(mIntentBroadcast);
    }

    @Override
    public boolean isPlaying() {
        return mMediaPlayer.isPlaying();
    }

    @Override
    public void play(String url) {
        try {
            mMediaPlayer = new MediaPlayer();
            mMediaPlayer.setDataSource(mContext, Uri.parse(url));
            mMediaPlayer.prepareAsync();
            mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mMediaPlayer.setVolume(Constants.Stream.LEFT_VOLUME, Constants.Stream.RIGHT_VOLUME);
            mMediaPlayer.setOnPreparedListener(this);
            mMediaPlayer.setOnCompletionListener(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stop() {
        if (mMediaPlayer != null) {
            mMediaPlayer.stop();
            mIntentBroadcast.putExtra(EXTRA_STATE_MEDIA, false);
            sendBroadcast(mIntentBroadcast);
        }
    }

    @Override
    public void pause() {
        if (mMediaPlayer != null && !isPlaying()) {
            mOnMediaPlayerChangeListener.onMediaStateChanged(true);
            mMediaPlayer.start();
        } else {
            mOnMediaPlayerChangeListener.onMediaStateChanged(false);
            mMediaPlayer.pause();
        }
    }

    public void resetButtonPlay() {
        mOnMediaPlayerChangeListener.onMediaStateChanged(true);
    }

    @Override
    public Track getCurrentTrack() {
        return mTracks.get(mPosition);
    }

    @Override
    public int getDuration() {
        return getDurationTrackCurrent();
    }

    @Override
    public void next() {
        destroyMusic();
        if (mTypeShuffle == ShuffleMode.SHUFFLE_ON) {
            mPosition = mRandom.nextInt(mTracks.size() - 1);
        } else if (mPosition == mTracks.size() - 1) {
            mPosition = 0;
        } else {
            mPosition++;
        }
        changeStateUI();
    }

    @Override
    public void previous() {
        destroyMusic();
        if (mTypeShuffle == ShuffleMode.SHUFFLE_ON) {
            mPosition = mRandom.nextInt(mTracks.size() - 1);
        } else if (mPosition == 0) {
            mPosition = mTracks.size() - 1;
        } else {
            mPosition--;
        }
        changeStateUI();
    }

    public void changeStateUI() {
        resetButtonPlay();
        changeTrack();
        play(getUrlCurrent());
    }

    @Override
    public void release() {
        mMediaPlayer.release();
    }

    public void changeTrack() {
        mOnMediaPlayerChangeListener.onTrackChange(getCurrentTrack());
    }

    @Override
    public void seek(int duration) {
        mMediaPlayer.seekTo(duration);
    }

    @Override
    public void download() {
        switch (mTypeDownload) {
            case DOWNLOADABLE:
                mTypeDownload = DownloadMode.DOWNLOADED;
                break;
            default:
                break;
        }
        mOnMediaPlayerChangeListener.onDownloadStateChange(mTypeDownload);
    }

    @Override
    public void shuffle() {
        switch (mTypeShuffle) {
            case SHUFFLE_ON:
                mTypeShuffle = ShuffleMode.SHUFFLE_OFF;
                break;
            case SHUFFLE_OFF:
                mTypeShuffle = ShuffleMode.SHUFFLE_ON;
                break;
            default:
                break;
        }
        mOnMediaPlayerChangeListener.onShuffleStateChange(mTypeShuffle);
    }

    @Override
    public void loop() {
        switch (mTypeLoop) {
            case LOOP_OFF:
                mTypeLoop = LoopMode.LOOP_ONE;
                break;
            case LOOP_ONE:
                mTypeLoop = LoopMode.LOOP_OFF;
                break;
            case LOOP_ALL:
                mTypeLoop = LoopMode.LOOP_OFF;
                break;
            default:
                break;
        }
        mOnMediaPlayerChangeListener.onLoopStateChange(mTypeLoop);
    }

    @Override
    public int getCurrentDuration() {
        return mMediaPlayer.getCurrentPosition();
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        checkLoopMode();
    }

    private void checkLoopMode() {
        switch (mTypeLoop) {
            case LoopMode.LOOP_OFF:
                if (mPosition < mTracks.size() - 1) {
                    mPosition++;
                    play(getUrlCurrent());
                } else {
                    mPosition = 0;
                    seek(0);
                    stop();
                }
                changeTrack();
                break;
            case LoopMode.LOOP_ONE:
                play(getUrlCurrent());
                changeTrack();
                mPosition++;
                break;
            case LoopMode.LOOP_ALL:
                if (mPosition == mTracks.size() - 1) {
                    mPosition = 0;
                } else {
                    mPosition++;
                }
                play(getUrlCurrent());
                break;
        }
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        mp.start();
    }

    public class MusicBinder extends Binder {
        public MusicService getService() {
            return MusicService.this;
        }
    }

    public int getDurationTrackCurrent() {
        return mMediaPlayer.getDuration();
    }

    public void setOnMediaPlayerChangeListener(
            OnMediaPlayerChangeListener onMediaPlayerChangeListener) {
        mOnMediaPlayerChangeListener = onMediaPlayerChangeListener;
    }
}
