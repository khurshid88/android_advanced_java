package com.example.android_advanced_java.activity.network.retrofit.services;

import com.example.android_advanced_java.activity.model.Poster;
import com.example.android_advanced_java.activity.model.PosterResp;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.http.*;

public interface PosterService {
    @Headers(
            "Content-type:application/json"
    )

    @GET("posts")
    Call<ArrayList<PosterResp>> listPost();

    @GET("posts/{id}")
    Call<PosterResp> singlePost(@Path("id") int id);

    @POST("posts")
    Call<PosterResp> createPost(@Body Poster poster);

    @PUT("posts/{id}")
    Call<PosterResp> updatePost(@Path("id") int id, @Body Poster post);

    @DELETE("posts/{id}")
    Call<PosterResp> deletePost(@Path("id") int id);
}
