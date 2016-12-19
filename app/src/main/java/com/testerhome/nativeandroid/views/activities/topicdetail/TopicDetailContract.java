package com.testerhome.nativeandroid.views.activities.topicdetail;

import com.testerhome.nativeandroid.data.remote.model.TopicDetailResponse;
import com.testerhome.nativeandroid.views.activities.base.MvpPresenter;
import com.testerhome.nativeandroid.views.activities.base.MvpView;

/**
 * Created by libin on 2016/12/19.
 * Description:
 */

interface TopicDetailContract {

    interface View extends MvpView {
        void showTopicDetail(TopicDetailResponse response);
    }

    interface Presenter extends MvpPresenter<View>{
        void loadTopicDetail(String topicId);
    }
}
