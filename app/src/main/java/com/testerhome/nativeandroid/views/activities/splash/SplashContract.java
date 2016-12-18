package com.testerhome.nativeandroid.views.activities.splash;

import com.testerhome.nativeandroid.views.activities.base.MvpPresenter;
import com.testerhome.nativeandroid.views.activities.base.MvpView;

/**
 * Created by Bin Li on 2016/12/9.
 */

public interface SplashContract {

    interface View extends MvpView {
        void showProgress();

        void hideProgress();

        void showNoInetErrorMsg();

        void moveToMainView();
    }

    interface Presenter extends MvpPresenter<View> {
        void didFinishLoading();
    }
}
