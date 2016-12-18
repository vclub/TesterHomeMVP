package com.testerhome.nativeandroid.data;

import com.testerhome.nativeandroid.data.remote.THRestService;
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
}
