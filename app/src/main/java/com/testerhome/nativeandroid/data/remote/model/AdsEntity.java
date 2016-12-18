package com.testerhome.nativeandroid.data.remote.model;

/**
 * Created by Bin Li on 2016/12/18.
 */
public class AdsEntity {

    private String cover;
    private String created_at;
    private String topic_author;
    private String topic_id;
    private String topic_title;
    private String updated_at;

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getTopic_author() {
        return topic_author;
    }

    public void setTopic_author(String topic_author) {
        this.topic_author = topic_author;
    }

    public String getTopic_id() {
        return topic_id;
    }

    public void setTopic_id(String topic_id) {
        this.topic_id = topic_id;
    }

    public String getTopic_title() {
        return topic_title;
    }

    public void setTopic_title(String topic_title) {
        this.topic_title = topic_title;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }
}
