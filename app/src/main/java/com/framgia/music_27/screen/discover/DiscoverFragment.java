package com.framgia.music_27.screen.discover;

import android.os.Bundle;
import com.framgia.music_27.R;
import com.framgia.music_27.screen.base.BaseFragment;

public class DiscoverFragment extends BaseFragment {
    public static final String TAG = "DiscoverFragment";

    public static DiscoverFragment newInstance() {
        return new DiscoverFragment();
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_discover;
    }

    @Override
    protected void initComponents() {

    }

    @Override
    protected void initData(Bundle saveInstanceState) {

    }
}
