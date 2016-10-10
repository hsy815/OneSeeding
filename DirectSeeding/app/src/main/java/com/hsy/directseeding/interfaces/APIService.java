package com.hsy.directseeding.interfaces;

import com.hsy.directseeding.entity.LRRmodel;
import com.hsy.directseeding.entity.ResultModel;

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

}
