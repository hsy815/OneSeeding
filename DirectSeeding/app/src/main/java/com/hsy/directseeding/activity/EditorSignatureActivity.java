package com.hsy.directseeding.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.hsy.directseeding.BaseActivity;
import com.hsy.directseeding.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EditorSignatureActivity extends BaseActivity {

    @BindView(R.id.title_text)
    TextView titleText;
    @BindView(R.id.title_right_text)
    TextView titleRightText;
    @BindView(R.id.content_text)
    EditText contentText;
    @BindView(R.id.number_text)
    TextView numberText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor_signature);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {

        titleText.setText("编辑个性签名");
        titleRightText.setVisibility(View.VISIBLE);
        contentText.addTextChangedListener(new TextWatcher() {
            private CharSequence temp;
            private int selectionStart;
            private int selectionEnd;

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                temp = s;
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                int number = s.length();
                numberText.setText("" + number);
                selectionStart = contentText.getSelectionStart();
                selectionEnd = contentText.getSelectionEnd();
                if (temp.length() > 32) {
                    s.delete(selectionStart - 1, selectionEnd);
                    int tempSelection = selectionStart;
                    contentText.setText(s);
                    contentText.setSelection(tempSelection); // 设置光标在最后
                }
            }
        });
    }

    @OnClick(R.id.title_right_text)
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.title_right_text:

                break;
        }
    }
}
