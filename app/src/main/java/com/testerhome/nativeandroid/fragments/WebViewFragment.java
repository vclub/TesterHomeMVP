package com.testerhome.nativeandroid.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.webkit.WebView;

import com.testerhome.nativeandroid.R;
import com.testerhome.nativeandroid.fragments.base.BaseFragment;

import butterknife.BindView;

/**
 * Created by Bin Li on 2016/12/9.
 */

public class WebViewFragment extends BaseFragment {

    @BindView(R.id.demo)
    WebView mWebView;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_webview;
    }

    @Override
    protected void setupView() {
        super.setupView();
        mWebView.loadUrl("https://www.bing.com");
    }
}
