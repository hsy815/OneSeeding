package com.hsy.directseeding.interfaces;

import com.hsy.directseeding.entity.LRRmodel;
import com.hsy.directseeding.entity.ResultModel;
import com.hsy.directseeding.entity.pushUrl;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by hsy on 16/4/18.
 */
public interface APIService {


    /**
     * 登录
     *
     * @param mobile
     * @param password
     * @return
     */
    @FormUrlEncoded
    @POST("login/index")
    Call<ResultModel<LRRmodel>> login(@Field("mobile") CharSequence mobile, @Field("password") CharSequence password);

    /**
     * 获取推流地址
     *
     * @param hostId
     * @return
     */
    @FormUrlEncoded
    @POST("live/streams/getPub")
    Call<ResultModel<pushUrl>> getPushUrl(@Field("hostId") CharSequence hostId);
}
