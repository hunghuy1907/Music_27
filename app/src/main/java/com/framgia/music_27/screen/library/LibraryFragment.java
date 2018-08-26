package com.framgia.music_27.screen.library;

import android.os.Bundle;
import com.framgia.music_27.R;
import com.framgia.music_27.screen.base.BaseFragment;

public class LibraryFragment extends BaseFragment {

    public static final String TAG = "LibraryFragment";

    public static LibraryFragment newInstance() {
        return new LibraryFragment();
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_library;
    }

    @Override
    protected void initComponents() {

    }

    @Override
    protected void initData(Bundle saveInstanceState) {

    }
}
