package com.testerhome.nativeandroid.views.activities.topicreply;

import com.testerhome.nativeandroid.data.remote.model.TopicReplyResponse;
import com.testerhome.nativeandroid.views.activities.base.MvpPresenter;
import com.testerhome.nativeandroid.views.activities.base.MvpView;

/**
 * Created by libin on 2016/12/19.
 * Description:
 */

interface TopicReplyContract {

    interface View extends MvpView{
        void showTopicReplies(TopicReplyResponse response);
        void hideEmptyView();
    }

    interface Presenter extends MvpPresenter<View>{
        void loadTopicReplies(String topicId, int offset);
    }
}
