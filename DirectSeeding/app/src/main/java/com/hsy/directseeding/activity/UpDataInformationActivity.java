package com.hsy.directseeding.activity;

import android.os.Bundle;
import android.view.View;
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

    @OnClick(R.id.title_right_text)
    public void onClick() {
    }
}
