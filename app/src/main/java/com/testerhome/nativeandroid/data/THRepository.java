package com.testerhome.nativeandroid.data;

import com.testerhome.nativeandroid.data.remote.model.TopicsResponse;

import io.reactivex.Observable;

/**
 * Created by Bin Li on 2016/12/18.
 */

public interface THRepository {

    Observable<TopicsResponse> getTopicList(String type, int offset);
}
