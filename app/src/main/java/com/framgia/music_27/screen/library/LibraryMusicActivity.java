package com.framgia.music_27.screen.library;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import com.framgia.music_27.R;
import com.framgia.music_27.screen.base.BaseActivity;
import com.framgia.music_27.screen.download.DownloadTrackFragment;
import com.framgia.music_27.screen.local_music.LocalTrackFragment;

public class LibraryMusicActivity extends BaseActivity {
    public static final String EXTRA_TAG = "com.framgia.music_27.EXTRA_TAG";

    public static Intent getIntentLocalTrackActivity(Context context, String tag) {
        Intent intent = new Intent(context, LibraryMusicActivity.class);
        intent.putExtra(EXTRA_TAG, tag);
        return intent;
    }

    @Override
    protected int getLayoutResources() {
        return R.layout.activity_library;
    }

    @Override
    protected void initComponents() {

    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_library, getFragment(getTag()))
                .commit();
    }

    public String getTag() {
        return getIntent().getStringExtra(EXTRA_TAG);
    }

    private Fragment getFragment(String tag) {
        switch (tag) {
            case LocalTrackFragment.TAG:
                return LocalTrackFragment.getInstance();

            case DownloadTrackFragment.TAG:
                return DownloadTrackFragment.getInstance();
        }
        return null;
    }
}
