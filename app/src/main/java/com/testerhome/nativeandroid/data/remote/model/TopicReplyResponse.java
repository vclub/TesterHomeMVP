package com.testerhome.nativeandroid.data.remote.model;

import java.util.List;

/**
 * Created by libin on 2016/12/19.
 * Description:
 */
public class TopicReplyResponse {

    private List<TopicReplyEntity> replies;

    public  List<TopicReplyEntity> getTopicReply(){
        return replies;
    }

    public void setTopicReply(List<TopicReplyEntity> replies){
        this.replies = replies;
    }
}
