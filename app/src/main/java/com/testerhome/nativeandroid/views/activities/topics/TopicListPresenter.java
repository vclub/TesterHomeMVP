package com.testerhome.nativeandroid.views.activities.topics;

import android.util.Log;

import com.testerhome.nativeandroid.data.THRepository;
import com.testerhome.nativeandroid.views.activities.base.BasePresenter;

import io.reactivex.Scheduler;

import static android.content.ContentValues.TAG;

/**
 * Created by Bin Li on 2016/12/18.
 */

public class TopicListPresenter extends BasePresenter<TopicContract.View>
        implements TopicContract.Presenter {

    private final Scheduler mainScheduler, ioScheduler;
    private THRepository mTHRepository;

    public TopicListPresenter(THRepository THRepository, Scheduler mainScheduler, Scheduler ioScheduler) {
        this.mainScheduler = mainScheduler;
        this.ioScheduler = ioScheduler;
        this.mTHRepository = THRepository;
    }

    @Override
    public void getTopics(String type, int offset) {
        checkViewAttached();
        addDisposable(mTHRepository.getTopicList(type, offset)
                .subscribeOn(ioScheduler)
                .observeOn(mainScheduler)
                .subscribe(topicsResponse -> {
                            getView().showTopics(topicsResponse);
                        },
                        error -> {
                            Log.e(TAG, "getTopics: ", error);
                        }));
    }
}
