package com.jobchumo.newsapi.apis;

import com.jobchumo.newsapi.model.ResponseModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIInterface {
    @GET("top-headlines")
    Call<ResponseModel> getLatestNews(@Query("country") String source, @Query("apiKey") String apiKey);
}
