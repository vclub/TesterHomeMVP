package com.testerhome.nativeandroid.data;

import com.testerhome.nativeandroid.data.remote.model.TopicDetailResponse;
import com.testerhome.nativeandroid.data.remote.model.TopicReplyResponse;
import com.testerhome.nativeandroid.data.remote.model.TopicsResponse;

import io.reactivex.Observable;

/**
 * Created by Bin Li on 2016/12/18.
 */

public interface THRepository {

    Observable<TopicsResponse> getTopicList(String type, int offset);


    Observable<TopicDetailResponse> getTopicDetail(String topicId);

    Observable<TopicReplyResponse> getTopicsReplies(String topicId, int offset);
}
