package com.hsy.directseeding.net;

import android.util.Base64;

import com.hsy.directseeding.interfaces.APIService;
import com.hsy.directseeding.uitl.Variable;

import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by hsy on 16/4/18.
 */
public class RetrofitNet {

    public static final String BASE_URL = "http://wonapi.maxleap.cn/1.0/";

    public static APIService request() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient())
                .build();
        return retrofit.create(APIService.class);
    }

    public static APIService headerRequest() {
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request()
                        .newBuilder()
                        .addHeader("Authorization", "Basic " + Base64.encodeToString(Variable.ACCESS_TOKEN.getBytes(), Base64.NO_WRAP))
                        .build();
                return chain.proceed(request);
            }
        }).build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        return retrofit.create(APIService.class);
    }
}
