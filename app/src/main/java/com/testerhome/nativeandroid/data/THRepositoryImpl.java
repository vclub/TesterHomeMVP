package com.testerhome.nativeandroid.data;

import com.testerhome.nativeandroid.data.remote.THRestService;
import com.testerhome.nativeandroid.data.remote.model.TopicDetailResponse;
import com.testerhome.nativeandroid.data.remote.model.TopicReplyResponse;
import com.testerhome.nativeandroid.data.remote.model.TopicsResponse;

import io.reactivex.Observable;

/**
 * Created by Bin Li on 2016/12/18.
 */

public class THRepositoryImpl implements THRepository {

    private THRestService mTHRestService;

    public THRepositoryImpl(THRestService THRestService) {
        mTHRestService = THRestService;
    }

    @Override
    public Observable<TopicsResponse> getTopicList(String type, int offset) {
        return mTHRestService.getTopicsByType(type, offset);
    }

    @Override
    public Observable<TopicDetailResponse> getTopicDetail(String topicId) {
        return mTHRestService.getTopicById(topicId);
    }

    @Override
    public Observable<TopicReplyResponse> getTopicsReplies(String topicId, int offset) {
        return mTHRestService.getTopicsReplies(topicId, offset);
    }


}
