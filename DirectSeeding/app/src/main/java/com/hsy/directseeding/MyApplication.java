package com.hsy.directseeding;

import android.app.Application;

import com.qiniu.pili.droid.streaming.StreamingEnv;
import com.squareup.leakcanary.LeakCanary;

/**
 * Created by hsy on 16/9/29.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        StreamingEnv.init(getApplicationContext());
        LeakCanary.install(this);
    }
}
