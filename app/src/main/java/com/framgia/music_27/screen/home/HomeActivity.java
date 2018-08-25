package com.framgia.music_27.screen.home;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.os.Bundle;
import android.view.MenuItem;
import android.support.v4.app.Fragment;
import com.framgia.music_27.R;
import com.framgia.music_27.screen.base.BaseActivity;
import com.framgia.music_27.screen.discover.DiscoverFragment;
import com.framgia.music_27.screen.library.LibraryFragment;
import com.framgia.music_27.screen.search.SearchFragment;

public class HomeActivity extends BaseActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    private BottomNavigationView mBottomNavigationView;

    @Override
    protected int getLayoutResources() {
        return R.layout.activity_home;
    }

    @Override
    protected void initComponents() {
        mBottomNavigationView = findViewById(R.id.navigation_music);
        mBottomNavigationView.setOnNavigationItemSelectedListener(this);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        addFragment(DiscoverFragment.newInstance(), DiscoverFragment.TAG);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        String selectedTag;
        switch (item.getItemId()) {
            default:
            case R.id.action_home:
                selectedTag = DiscoverFragment.TAG;
                break;
            case R.id.action_search:
                selectedTag = SearchFragment.TAG;
                break;
            case R.id.action_library:
                selectedTag = LibraryFragment.TAG;
                break;
        }
        addFragment(selectedTag);
        return true;
    }

    private void addFragment(String tag) {
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(tag);
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .show(fragment)
                    .commit();
            return;
        }
        addFragment(getFragment(tag), tag);
    }

    private Fragment getFragment(String tag) {
        Fragment fragment = null;
        switch (tag) {
            case DiscoverFragment.TAG:
                fragment = DiscoverFragment.newInstance();
                break;
            case SearchFragment.TAG:
                fragment = SearchFragment.newInstance();
                break;
            case LibraryFragment.TAG:
                fragment = LibraryFragment.newInstance();
                break;
        }
        return fragment;
    }

    private void addFragment(Fragment fragment, String tag) {
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.frame_main, fragment, tag)
                .commit();
    }
}
