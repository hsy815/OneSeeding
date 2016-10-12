package com.hsy.directseeding;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.hsy.directseeding.uitl.Variable;
import com.maxleap.GetCallback;
import com.maxleap.MLDataManager;
import com.maxleap.MLInstallation;
import com.maxleap.MLInstallationManager;
import com.maxleap.MLObject;
import com.maxleap.MaxLeap;
import com.maxleap.TestUtils;
import com.maxleap.exception.MLException;
import com.maxleap.im.MLParrot;
import com.qiniu.pili.droid.streaming.StreamingEnv;
import com.squareup.leakcanary.LeakCanary;

/**
 * Created by hsy on 16/9/29.
 */

public class MyApplication extends Application {

    public static Context context;
    public static MLParrot parrot;

    @Override
    public void onCreate() {
        super.onCreate();

        context = getApplicationContext();
        MaxLeap.Options options = new MaxLeap.Options();
        options.appId = Variable.APPLICATION_ID;
        options.clientKey = Variable.CLIENT_KEY;
        options.serverRegion = MaxLeap.REGION_CN;
        MaxLeap.initialize(this, options);
        StreamingEnv.init(getApplicationContext());
        LeakCanary.install(this);
        Variable.INSTALLATIONID = MLInstallation.getCurrentInstallation().getInstallationId();
        parrot = MLParrot.getInstance();
    }
}
