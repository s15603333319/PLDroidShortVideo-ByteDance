package com.qiniu.pili.droid.shortvideo.demo.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;


abstract public class BaseFragment<T extends IPresenter> extends Fragment implements IView {
    protected T mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected void setPresenter(T presenter) {
        assert presenter != null;
        mPresenter = presenter;
        mPresenter.attachView(this);
    }

    @Override
    public void onDestroy() {
        if (mPresenter != null) {
            mPresenter.detachView();
        }
        super.onDestroy();
    }
}
