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

public class MineActivity extends BaseActivity {

    @BindView(R.id.title_text)
    TextView titleText;
    @BindView(R.id.mine_user_layout)
    RelativeLayout mineUserLayout;
    @BindView(R.id.mine_setting_layout)
    RelativeLayout mineSettingLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {

        titleText.setText("我");

    }

    @OnClick({R.id.mine_user_layout, R.id.mine_setting_layout})
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.mine_user_layout:
                intent = new Intent(MineActivity.this, UserInformationActivity.class);
                startActivity(intent);
                break;
            case R.id.mine_setting_layout:
                intent = new Intent(MineActivity.this, SettingActivity.class);
                startActivity(intent);
                break;
        }
    }
}
