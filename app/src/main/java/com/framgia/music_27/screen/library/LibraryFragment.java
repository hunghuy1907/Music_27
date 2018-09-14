package com.framgia.music_27.screen.library;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.framgia.music_27.R;
import com.framgia.music_27.data.repository.TrackRepository;
import com.framgia.music_27.data.source.local.TrackLocalDataSource;
import com.framgia.music_27.data.source.remote.TrackRemoteDataResource;
import com.framgia.music_27.screen.base.BaseFragment;
import com.framgia.music_27.screen.download.DownloadTrackFragment;
import com.framgia.music_27.screen.local_music.LocalTrackFragment;
import com.framgia.music_27.utils.Constants;

public class LibraryFragment extends BaseFragment implements View.OnClickListener, LibraryContract.View{

    public static final String TAG = "LibraryFragment";
    private TextView mTextFileMusicLocal;
    private TextView mTextFileMusicDownload;
    private TextView mTextTotalFileMusicLocal;
    private TextView mTextTotalFileMusicDownload;
    private LibraryContract.Presenter mPresenter;

    public static LibraryFragment newInstance() {
        return new LibraryFragment();
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_library;
    }

    @Override
    protected void initComponents() {
        mTextFileMusicLocal = getActivity().findViewById(R.id.text_music_file);
        mTextFileMusicDownload = getActivity().findViewById(R.id.text_download);
        mTextTotalFileMusicLocal = getActivity().findViewById(R.id.text_total_music_file);
        mTextTotalFileMusicDownload = getActivity().findViewById(R.id.text_total_download);
        mTextFileMusicLocal.setOnClickListener(this);
        mTextFileMusicDownload.setOnClickListener(this);
    }

    @Override
    protected void initData(Bundle saveInstanceState) {
        mPresenter = new LibraryPresent(TrackRepository
                .getInstance(TrackRemoteDataResource.getInstance(), TrackLocalDataSource.getsInstance(getActivity())));
        mPresenter.setView(this);
        mPresenter.getTotalLocalMusicLocal();
        mPresenter.getTrackDownload(Constants.Player.TYPE_INTEGER);
    }

    @Override
    public void onClick(View v) {
        String tag = null;
        switch (v.getId()) {
            case R.id.text_music_file:
                tag = LocalTrackFragment.TAG;
                break;
            case R.id.text_download:
                tag = DownloadTrackFragment.TAG;
                break;
            default:
                break;
        }
        getActivity().startActivity(LibraryMusicActivity.getIntentLocalTrackActivity(getActivity(), tag));
    }

    @Override
    public void onGetTotalLocalMusicSuccess(Integer integer) {
        mTextTotalFileMusicLocal.setText(integer + "");
    }

    @Override
    public void onGetTotalDownloadTrackSuccess(Integer integer) {
        mTextTotalFileMusicDownload.setText(integer + "");
    }
}
