package com.testerhome.nativeandroid.data.remote.model;

import java.util.List;

/**
 * Created by Bin Li on 2016/12/18.
 */

public class TopicsResponse {

    private List<TopicEntity> topics;

    public List<TopicEntity> getTopics() {
        return topics;
    }

    public void setTopics(List<TopicEntity> topics) {
        this.topics = topics;
    }
}
