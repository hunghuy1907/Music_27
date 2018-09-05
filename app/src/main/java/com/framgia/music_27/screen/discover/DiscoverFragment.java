package com.framgia.music_27.screen.discover;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.framgia.music_27.R;
import com.framgia.music_27.data.model.Genre;
import com.framgia.music_27.screen.base.BaseFragment;
import com.framgia.music_27.screen.discover.adapter.GenresAdapter;
import com.framgia.music_27.screen.discover.adapter.OnClickItem;
import com.framgia.music_27.screen.genre.GenreActivity;
import java.util.ArrayList;
import java.util.List;

public class DiscoverFragment extends BaseFragment implements DiscoverContract.View , OnClickItem{

    public static final String ARGUMENT_GENRE = "ARGUMENT_GENRE";
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
        mGenres = new ArrayList<>();
        if (getArguments() != null) {
            mGenres = getArguments().getParcelableArrayList(ARGUMENT_GENRE);
        }
        initRecycleGenres();
    }

    private void initRecycleGenres() {
        GenresAdapter genresAdapter = new GenresAdapter(getActivity(), mGenres, this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false);
        mRecycleGenres.setLayoutManager(linearLayoutManager);
        mRecycleGenres.setAdapter(genresAdapter);
    }

    public static DiscoverFragment newInstance(ArrayList<Genre> genres) {
        DiscoverFragment fragment = new DiscoverFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(ARGUMENT_GENRE, genres);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void clickItem(int position) {
        getActivity().startActivity(GenreActivity.getIntentProfile(getActivity(),
                mGenres.get(position).getName()));
    }
}
