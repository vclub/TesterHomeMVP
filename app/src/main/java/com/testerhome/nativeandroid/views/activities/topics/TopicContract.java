package com.testerhome.nativeandroid.views.activities.topics;

import com.testerhome.nativeandroid.data.remote.model.TopicsResponse;
import com.testerhome.nativeandroid.views.activities.base.MvpPresenter;
import com.testerhome.nativeandroid.views.activities.base.MvpView;

/**
 * Created by Bin Li on 2016/12/18.
 */

public interface TopicContract {

    interface View extends MvpView {
        void showTopics(TopicsResponse topicsResponse);
    }

    interface Presenter extends MvpPresenter<View> {
        void getTopics(String type, int offset);
    }
}
