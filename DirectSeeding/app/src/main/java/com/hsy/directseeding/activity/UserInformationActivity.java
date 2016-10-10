package com.hsy.directseeding.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hsy.directseeding.BaseActivity;
import com.hsy.directseeding.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UserInformationActivity extends BaseActivity {

    @BindView(R.id.title_text)
    TextView titleText;
    @BindView(R.id.user_name_layout)
    RelativeLayout userNameLayout;
    @BindView(R.id.user_img_layout)
    RelativeLayout userImgLayout;
    @BindView(R.id.user_signature_layout)
    RelativeLayout userSignatureLayout;
    @BindView(R.id.user_home_layout)
    RelativeLayout userHomeLayout;
    @BindView(R.id.title_back)
    LinearLayout titleBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_information);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {

        titleText.setText("个人资料");

    }

    @OnClick({R.id.user_name_layout, R.id.user_img_layout, R.id.user_signature_layout, R.id.user_home_layout, R.id.title_back})
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.user_name_layout:
                intent = new Intent(UserInformationActivity.this, UpDataInformationActivity.class);
                startActivity(intent);
                break;
            case R.id.user_img_layout:
                break;
            case R.id.user_signature_layout:
                intent = new Intent(UserInformationActivity.this, EditorSignatureActivity.class);
                startActivity(intent);
                break;
            case R.id.user_home_layout:
                break;
            case R.id.title_back:
                finish();
                break;
        }
    }

}
