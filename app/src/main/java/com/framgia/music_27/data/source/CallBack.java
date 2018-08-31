package com.framgia.music_27.data.source;

public interface CallBack<T> {

    void getDataSuccess(T datas);

    void getDataFailure(Exception e);
}
