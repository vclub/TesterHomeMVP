package com.testerhome.nativeandroid.fragments.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Bin Li on 2016/12/9.
 */

public abstract class BaseFragment extends Fragment {

    protected CompositeDisposable mCompositeDisposable;
    protected Unbinder mUnbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutRes(), container, false);
        mUnbinder = ButterKnife.bind(this, view);

        setupView();
        return view;
    }

    protected void setupView() {

    }

    @LayoutRes
    protected abstract int getLayoutRes();

    @Override
    public void onDestroyView() {
        mUnbinder.unbind();
        rxDispose();
        super.onDestroyView();
    }

    protected void rxDispose() {
        if (mCompositeDisposable != null && !mCompositeDisposable.isDisposed()) {
            mCompositeDisposable.dispose();
        }
    }
}
