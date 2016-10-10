package com.hsy.directseeding.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hsy.directseeding.BaseActivity;
import com.hsy.directseeding.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettingActivity extends BaseActivity {

    @BindView(R.id.title_text)
    TextView titleText;
    @BindView(R.id.setting_about_layout)
    RelativeLayout settingAboutLayout;
    @BindView(R.id.setting_share_layout)
    RelativeLayout settingShareLayout;

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

    @OnClick({R.id.setting_about_layout, R.id.setting_share_layout})
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.setting_about_layout:
                intent = new Intent(SettingActivity.this, AboutMineActivity.class);
                startActivity(intent);
                break;
            case R.id.setting_share_layout:
                break;
        }
    }
}
