package com.hsy.directseeding.uitl;/**
 * Created by hsy on 16/10/11.
 */

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import com.hsy.directseeding.MyApplication;

/**
 * 类名: Constant
 * Created by hsy on 16/10/11.
 */
public class Constant {

    /**
     * 检查是否有网络
     * @return
     */
    public static boolean isNetworkConnected() {
        ConnectivityManager mConnectivityManager = (ConnectivityManager) MyApplication.context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
        if (mNetworkInfo != null) {
            return mNetworkInfo.isAvailable();
        }
        return false;
    }

    public static void toast_s(String content){
        Toast.makeText(MyApplication.context,content,Toast.LENGTH_SHORT).show();
    }

    public static void toast_l(String content){
        Toast.makeText(MyApplication.context,content,Toast.LENGTH_LONG).show();
    }

}
