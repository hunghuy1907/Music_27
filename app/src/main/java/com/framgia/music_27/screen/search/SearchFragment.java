package com.framgia.music_27.screen.search;

import android.os.Bundle;

import com.framgia.music_27.R;
import com.framgia.music_27.screen.base.BaseFragment;

public class SearchFragment extends BaseFragment {
    public static final String TAG = "SearchFragment";

    public static SearchFragment newInstance() {
        return new SearchFragment();
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_search;
    }

    @Override
    protected void initComponents() {

    }

    @Override
    protected void initData(Bundle saveInstanceState) {

    }
}
