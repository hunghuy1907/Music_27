package com.framgia.music_27.screen.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Sun on 3/11/2017.
 */

public abstract class BaseFragment extends Fragment {

    protected abstract int getLayoutResource();

    protected abstract void initComponents();

    protected abstract void initData(Bundle saveInstanceState);

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(getLayoutResource(), container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initComponents();
        initData(savedInstanceState);
    }

    public BaseActivity getBaseActivity(){
        if (getActivity() instanceof BaseActivity){
            return (BaseActivity) getActivity();
        }
        return null;
    }
}
