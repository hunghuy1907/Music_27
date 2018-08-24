package com.framgia.music_27.screen.base;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by le.quang.dao on 10/03/2017.
 */

public abstract class BaseActivity extends AppCompatActivity {

    protected abstract int getLayoutResources();

    protected abstract void initComponents();

    protected abstract void initData(Bundle savedInstanceState);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResources());
        initComponents();
        initData(savedInstanceState);
    }
}
