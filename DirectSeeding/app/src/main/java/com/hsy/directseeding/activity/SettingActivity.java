package com.hsy.directseeding.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hsy.directseeding.BaseActivity;
import com.hsy.directseeding.R;
import com.hsy.directseeding.uitl.ScoreUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettingActivity extends BaseActivity {

    private static String SHARE_URL = "http://sj.qq.com/myapp/detail.htm?apkName=max.com.dwxs";
    private static String DOWNLOAD_URL = "http://migmkt.qq.com/g/myapp/yyb-common.html?ADTAG=buy.bd.yyb01";
    @BindView(R.id.title_text)
    TextView titleText;
    @BindView(R.id.setting_about_layout)
    RelativeLayout settingAboutLayout;
    @BindView(R.id.setting_share_layout)
    RelativeLayout settingShareLayout;
    @BindView(R.id.title_back)
    LinearLayout titleBack;
    @BindView(R.id.setting_evaluate_layout)
    RelativeLayout settingEvaluateLayout;
    @BindView(R.id.setting_opinion_layout)
    RelativeLayout settingOpinionLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {

        titleText.setText("设置");

    }

    @OnClick({R.id.setting_about_layout, R.id.setting_share_layout, R.id.title_back,R.id.setting_evaluate_layout, R.id.setting_opinion_layout})
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.setting_about_layout:
                intent = new Intent(SettingActivity.this, AboutMineActivity.class);
                startActivity(intent);
                break;
            case R.id.setting_share_layout:
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, SHARE_URL);
                sendIntent.setType("text/plain");
                startActivity(Intent.createChooser(sendIntent, "我要分享"));
                break;
            case R.id.setting_evaluate_layout:
            case R.id.setting_opinion_layout:
                if(ScoreUtils.isAvilible(SettingActivity.this,"com.tencent.android.qqdownloader")) {
                    ScoreUtils.launchAppDetail("max.com.dwxs", "com.tencent.android.qqdownloader");
                }else{
                    AlertDialog.Builder builder = new AlertDialog.Builder(SettingActivity.this);
                    builder.setTitle("提示").setMessage("您没有安装应用宝,是否安装应用宝?");
                    builder.setPositiveButton("是", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Uri uri = Uri.parse(DOWNLOAD_URL);
                            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                            startActivity(intent);
                            dialog.dismiss();
                        }
                    }).setNegativeButton("否", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    }).create().show();

                }
                break;
            case R.id.title_back:
                finish();
                break;
        }
    }

}
