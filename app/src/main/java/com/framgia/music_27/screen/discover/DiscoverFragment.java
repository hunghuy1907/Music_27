package com.framgia.music_27.screen.discover;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.framgia.music_27.R;
import com.framgia.music_27.data.model.Genre;
import com.framgia.music_27.screen.base.BaseFragment;
import com.framgia.music_27.screen.discover.adapter.GenresAdapter;
import java.util.List;

public class DiscoverFragment extends BaseFragment implements DiscoverContract.View{

    public static final String TAG = "DiscoverFragment";
    private RecyclerView mRecycleGenres;
    private List<Genre> mGenres;

    public static DiscoverFragment newInstance() {
        return new DiscoverFragment();
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_discover;
    }

    @Override
    protected void initComponents() {
        mRecycleGenres = getActivity().findViewById(R.id.recycle_genres);
    }

    @Override
    protected void initData(Bundle saveInstanceState) {
        initRecycleGenres();
    }

    private void initRecycleGenres() {
        GenresAdapter genresAdapter = new GenresAdapter(getActivity(), mGenres);;
        mRecycleGenres.setLayoutManager(new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false));
        mRecycleGenres.setAdapter(genresAdapter);
    }
}
