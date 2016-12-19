package com.testerhome.nativeandroid.views.activities.topicdetail;

import android.util.Log;

import com.testerhome.nativeandroid.data.THRepository;
import com.testerhome.nativeandroid.views.activities.base.BasePresenter;

import io.reactivex.Scheduler;

import static android.content.ContentValues.TAG;

/**
 * Created by libin on 2016/12/19.
 * Description:
 */

public class TopicDetailPresenter extends BasePresenter<TopicDetailContract.View> implements TopicDetailContract.Presenter {

    private final Scheduler mainScheduler, ioScheduler;
    private THRepository mTHRepository;

    public TopicDetailPresenter(Scheduler mainScheduler, Scheduler ioScheduler, THRepository mTHRepository) {
        this.mainScheduler = mainScheduler;
        this.ioScheduler = ioScheduler;
        this.mTHRepository = mTHRepository;
    }


    @Override
    public void loadTopicDetail(String topicId) {

        checkViewAttached();
        addDisposable(
                mTHRepository.getTopicDetail(topicId)
                        .subscribeOn(ioScheduler)
                        .observeOn(mainScheduler)
                        .subscribe(topicDetailResponse -> {
                                    getView().showTopicDetail(topicDetailResponse);
                                },
                                error -> {
                                    Log.e(TAG, "getTopics: ", error);
                                }));
    }
}
