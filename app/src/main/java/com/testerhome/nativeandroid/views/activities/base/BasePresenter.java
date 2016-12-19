package com.testerhome.nativeandroid.views.activities.base;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by Bin Li on 2016/12/9.
 */

public class BasePresenter<T extends MvpView> implements MvpPresenter<T> {

    private T view;
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    @Override
    public void attachView(T mvpView) {
        view = mvpView;
    }

    @Override
    public void detachView() {
        if (isViewAttached()) {
            mCompositeDisposable.clear();
            view = null;
        }
    }

    public T getView() {
        return view;
    }

    public void checkViewAttached() {
        if (!isViewAttached()) {
            throw new MvpViewNotAttachedException();
        }
    }

    private boolean isViewAttached() {
        return view != null;
    }

    protected void addDisposable(Disposable disposable) {
        this.mCompositeDisposable.add(disposable);
    }

    public static class MvpViewNotAttachedException extends RuntimeException {
        public MvpViewNotAttachedException() {
            super("Please call Presenter.attachView(MvpView) before" + " requesting data to the Presenter");
        }
    }
}
