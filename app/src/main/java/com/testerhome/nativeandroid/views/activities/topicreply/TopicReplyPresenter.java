package com.testerhome.nativeandroid.views.activities.topicreply;

import android.util.Log;

import com.testerhome.nativeandroid.data.THRepository;
import com.testerhome.nativeandroid.views.activities.base.BasePresenter;

import io.reactivex.Scheduler;

import static android.content.ContentValues.TAG;

/**
 * Created by libin on 2016/12/19.
 * Description:
 */

public class TopicReplyPresenter extends BasePresenter<TopicReplyContract.View>
        implements TopicReplyContract.Presenter {

    private final Scheduler mainScheduler, ioScheduler;
    private THRepository mTHRepository;

    public TopicReplyPresenter(Scheduler mainScheduler, Scheduler ioScheduler, THRepository mTHRepository) {
        this.mainScheduler = mainScheduler;
        this.ioScheduler = ioScheduler;
        this.mTHRepository = mTHRepository;
    }

    @Override
    public void loadTopicReplies(String topicId, int offset) {
        checkViewAttached();
        addDisposable(mTHRepository.getTopicsReplies(topicId, offset)
                .subscribeOn(ioScheduler)
                .observeOn(mainScheduler)
                .subscribe(topicReplyResponse -> {
                            getView().hideEmptyView();
                            getView().showTopicReplies(topicReplyResponse);
                        },
                        error -> {
                            getView().hideEmptyView();
                            Log.e(TAG, "getTopics: ", error);
                        }));
    }
}
