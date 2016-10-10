package com.hsy.directseeding.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hsy.directseeding.BaseActivity;
import com.hsy.directseeding.R;
import com.hsy.directseeding.uitl.Config;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class StartActivity extends BaseActivity {

    private static final String url = "rtmp://pili-publish.maxwon.cn/maxwon-live/android-test?e=1476067382&token=zHJrjjwccB_n30O16CAixLvfJ_0bs0gftZv8oOoH:4Yrr6NGIKxNJOEJCVuOrA_8z9Wo=";
//    private static final String url = "rtmp://pili-publish.maxwon.cn/maxwon-live/android-test?e=1476067197&token=zHJrjjwccB_n30O16CAixLvfJ_0bs0gftZv8oOoH:ApfbKkoGRx7igAvGPG829pp9kD8=";
    private static final String url2 = "Your app server url which get PublishUrl";
    @BindView(R.id.mine_btn_img)
    ImageView mineBtnImg;
    @BindView(R.id.start_btn)
    TextView startBtn;

    private static final String TAG = "MainActivity";

    private String requestStreamJson() {
        try {
            // Replace "Your app server" by your app sever url which can get the StreamJson as the SDK's input.
            HttpURLConnection httpConn = (HttpURLConnection) new URL(url2).openConnection();
            httpConn.setRequestMethod("POST");
            httpConn.setConnectTimeout(5000);
            httpConn.setReadTimeout(10000);
            int responseCode = httpConn.getResponseCode();
            if (responseCode != HttpURLConnection.HTTP_OK) {
                return null;
            }

            int length = httpConn.getContentLength();
            if (length <= 0) {
                return null;
            }
            InputStream is = httpConn.getInputStream();
            byte[] data = new byte[length];
            int read = is.read(data);
            is.close();
            if (read <= 0) {
                return null;
            }
            return new String(data, 0, read);
        } catch (Exception e) {
            Log.e("Network error!", "Network error!");
        }
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        ButterKnife.bind(this);
        initview();
    }

    private void initview() {
       
    }

    @OnClick({R.id.mine_btn_img, R.id.start_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.mine_btn_img:
                startActivity(new Intent(StartActivity.this, MineActivity.class));
                break;
            case R.id.start_btn:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            // Get the stream json from http
                            String publishUrl = Config.EXTRA_PUBLISH_URL_PREFIX + url;
                            Log.e("publishUrl", publishUrl);
                            Intent intent = new Intent(StartActivity.this, CameraStreamingActivity.class);
                            intent.putExtra(Config.EXTRA_KEY_PUB_URL, publishUrl);
                            startActivity(intent);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
                break;
        }
    }
}
