package com.hsy.directseeding.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.hsy.directseeding.BaseActivity;
import com.hsy.directseeding.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @BindView(R.id.title_text)
    TextView titleText;
    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.visibility_pw)
    ImageView visibilityPw;
    @BindView(R.id.login_text)
    TextView loginText;

    private int VISIBILITY_PASSWORD = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        titleText.setText("登录");
    }

    @OnClick({R.id.visibility_pw, R.id.login_text})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.visibility_pw:
                switch (VISIBILITY_PASSWORD) {
                    case 1:
                        VISIBILITY_PASSWORD = 2;
                        password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                        break;
                    case 2:
                        VISIBILITY_PASSWORD = 1;
                        password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                        break;
                }
                break;
            case R.id.login_text:
                startActivity(new Intent(MainActivity.this, StartActivity.class));
                break;
        }
    }
}
