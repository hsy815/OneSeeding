package com.hsy.directseeding.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.hsy.directseeding.BaseActivity;
import com.hsy.directseeding.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AboutMineActivity extends BaseActivity {

    @BindView(R.id.title_text)
    TextView titleText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_mine);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {

        titleText.setText("关于我们");

    }
}
