package com.homework.testkeron.network;

import com.homework.testkeron.model.PostServerModel;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RedditService {

    @GET("top.json")
    Single<PostServerModel> getRedditPost(@Query("limit") int limit, @Query("after") String after);

}
