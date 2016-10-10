package com.hsy.directseeding.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.hsy.directseeding.BaseActivity;
import com.hsy.directseeding.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EndActivity extends BaseActivity {

    @BindView(R.id.end_time)
    TextView endTime;
    @BindView(R.id.end_look)
    TextView endLook;
    @BindView(R.id.end_follow)
    TextView endFollow;
    @BindView(R.id.end_btn)
    TextView endBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.end_btn)
    public void onClick() {
        finish();
    }
}
