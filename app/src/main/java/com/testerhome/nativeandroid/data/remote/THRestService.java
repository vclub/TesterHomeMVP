package com.testerhome.nativeandroid.data.remote;

import com.testerhome.nativeandroid.data.remote.model.BannerResponse;
import com.testerhome.nativeandroid.data.remote.model.TopicDetailResponse;
import com.testerhome.nativeandroid.data.remote.model.TopicReplyResponse;
import com.testerhome.nativeandroid.data.remote.model.TopicsResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Bin Li on 2016/12/18.
 */

public interface THRestService {

    @GET("ads/toutiao.json")
    Observable<BannerResponse> getBanner();

    @GET("topics.json")
    Observable<TopicsResponse> getTopicsByType(@Query("type") String type,
                                               @Query("offset") int offset);

    @GET("topics/{id}.json")
    Observable<TopicDetailResponse> getTopicById(@Path("id") String id);

    @GET("topics/{id}/replies.json")
    Observable<TopicReplyResponse> getTopicsReplies(@Path("id") String id,
                                                    @Query("offset") int offset);
}
