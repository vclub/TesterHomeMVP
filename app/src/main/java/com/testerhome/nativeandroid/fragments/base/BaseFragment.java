package com.testerhome.nativeandroid.fragments.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.testerhome.nativeandroid.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Bin Li on 2016/12/9.
 */

public abstract class BaseFragment extends Fragment {

    protected CompositeDisposable mCompositeDisposable;
    protected Unbinder mUnbinder;

    protected View mContentView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mContentView == null){
            mContentView = inflater.inflate(getLayoutRes(), container, false);
            mUnbinder = ButterKnife.bind(this, mContentView);

            setupView();
        } else {
            ViewGroup parent = (ViewGroup) mContentView.getParent();
            if (parent != null) {
                parent.removeView(mContentView);
            }
        }

        return mContentView;
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

    @Nullable
    @BindView(android.R.id.empty)
    protected View mEmptyView;

    public void showEmptyView(){
        if (mEmptyView != null){
            mEmptyView.setVisibility(View.VISIBLE);
        }
    }

    public void hideEmptyView(){
        if (mEmptyView != null){
            mEmptyView.setVisibility(View.GONE);
        }
    }

    @Nullable
    @BindView(R.id.empty_load)
    View mEmptyLoad;
    protected void hideLoading(){
        if (mEmptyLoad != null){
            mEmptyLoad.setVisibility(View.GONE);
        }
    }

    @Nullable
    @BindView(R.id.error_panel)
    View mErrorView;

    @Nullable
    @BindView(R.id.error_subtitle)
    TextView mErrorText;

    protected void showErrorView(String errorMessage){
        showEmptyView();
        if (mErrorView != null){
            if (mErrorText != null){
                mErrorText.setText(errorMessage);
            }
            mErrorView.setVisibility(View.VISIBLE);
            hideLoading();
        }
    }
}
