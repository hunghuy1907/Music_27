package com.framgia.music_27.data.source;

public interface CallBack<T> {

    void getDataSuccess(T data);

    void getDataFailure(Exception e);
}
