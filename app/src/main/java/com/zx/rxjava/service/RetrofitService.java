package com.zx.rxjava.service;

import com.zx.rxjava.GetVipListRespons;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by zx on 2017/1/18.
 */
public interface RetrofitService {

    @GET("JsonDataService.ashx")
    Call<GetVipListRespons> getVipList(@QueryMap Map<String, String> params);

}
