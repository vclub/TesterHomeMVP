package com.testerhome.nativeandroid.fragments;

import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.testerhome.nativeandroid.Config;
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
    protected void setupView() {
        super.setupView();
        if (mWebView != null){

            mWebView.setWebViewClient(new WebViewClient(){
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    return super.shouldOverrideUrlLoading(view, url);
                }
            });

            mWebView.loadUrl(Config.WIKI_URL);
        }
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_webview;
    }

}
