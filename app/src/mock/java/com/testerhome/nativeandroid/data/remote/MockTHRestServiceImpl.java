package com.testerhome.nativeandroid.data.remote;

import com.testerhome.nativeandroid.data.remote.model.BannerResponse;
import com.testerhome.nativeandroid.data.remote.model.TopicsResponse;

import io.reactivex.Observable;
import retrofit2.http.Query;

/**
 * Created by Bin Li on 2016/12/18.
 */

public class MockTHRestServiceImpl implements THRestService {

    public MockTHRestServiceImpl() {

    }

    @Override
    public Observable<BannerResponse> getBanner() {
        return Observable.just(new BannerResponse());
    }

    @Override
    public Observable<TopicsResponse> getTopicsByType(@Query("type") String type, @Query("offset") int offset) {
        return Observable.just(new TopicsResponse());
    }
}
