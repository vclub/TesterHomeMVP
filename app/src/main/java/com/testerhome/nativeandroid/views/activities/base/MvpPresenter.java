package com.testerhome.nativeandroid.views.activities.base;

/**
 * Created by Bin Li on 2016/12/18.
 */

public interface MvpPresenter<V extends MvpView> {

    void attachView(V mvpView);

    void detachView();
}
