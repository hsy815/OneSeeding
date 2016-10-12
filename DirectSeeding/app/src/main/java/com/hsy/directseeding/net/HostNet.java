package com.hsy.directseeding.net;/**
 * Created by hsy on 16/10/11.
 */

import com.hsy.directseeding.entity.ResultModel;
import com.hsy.directseeding.entity.pushUrl;

import retrofit2.Call;

/**
 * 类名: HostNet
 * Created by hsy on 16/10/11.
 */
public class HostNet {

    public static Call<ResultModel<pushUrl>> getPushUrl(CharSequence hostid){
        return RetrofitNet.request().getPushUrl(hostid);
    }

}
