package com.hsy.directseeding.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hsy.directseeding.BaseActivity;
import com.hsy.directseeding.R;
import com.hsy.directseeding.view.ClearEditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UpDataInformationActivity extends BaseActivity {

    @BindView(R.id.title_text)
    TextView titleText;
    @BindView(R.id.title_right_text)
    TextView titleRightText;
    @BindView(R.id.name_edit)
    ClearEditText nameEdit;
    @BindView(R.id.title_back)
    LinearLayout titleBack;
    @BindView(R.id.activity_up_data_information)
    LinearLayout activityUpDataInformation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_up_data_information);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {

        titleText.setText("编辑昵称");
        titleRightText.setVisibility(View.VISIBLE);

    }

    @OnClick({R.id.title_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                finish();
                break;
        }
    }
}
