package com.hsy.directseeding.activity;

import android.animation.LayoutTransition;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.transition.Transition;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hsy.directseeding.R;
import com.hsy.directseeding.adapter.NewsAdapter;
import com.hsy.directseeding.adapter.PropertyImgAdapter;
import com.hsy.directseeding.adapter.RecyclerAdapter;
import com.hsy.directseeding.entity.News;
import com.hsy.directseeding.view.CameraPreviewFrameView;
import com.hsy.directseeding.view.CircleImageView;
import com.hsy.directseeding.view.MyRecyclerView;
import com.qiniu.pili.droid.streaming.AVCodecType;
import com.qiniu.pili.droid.streaming.MediaStreamingManager;
import com.qiniu.pili.droid.streaming.WatermarkSetting;
import com.qiniu.pili.droid.streaming.widget.AspectFrameLayout;

import java.util.ArrayList;
import java.util.List;

public class CameraStreamingActivity extends StreamingBaseActivity implements View.OnClickListener {

    private LinearLayout status_top;
    private MyRecyclerView myRecycler;
    private PropertyImgAdapter recyclerAdapter;
    private ImageView image1;
    private ImageView image3;
    private ImageView image4;
    private ImageView image5;
    private RelativeLayout relativeLayout1;
    private RelativeLayout relativeLayout2;
    private EditText edit_querys;
    private Dialog dialog;
    private Dialog dialog2;
    private RelativeLayout camera_button_holder;
    private ImageView pop_quit;
    private TextView pop_name;
    private CircleImageView pop_img;
    private TextView pop_btn1;
    private TextView pop_btn2;
    private TextView dia_name;
    private TextView dia_btn1;
    private TextView dia_btn2;
    private ListView streaming_list;
    private NewsAdapter newsAdapter;
    private List<News> newList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //透明状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        AspectFrameLayout afl = (AspectFrameLayout) findViewById(R.id.cameraPreview_afl);
        afl.setShowMode(AspectFrameLayout.SHOW_MODE.FULL);
        CameraPreviewFrameView cameraPreviewFrameView =
                (CameraPreviewFrameView) findViewById(R.id.cameraPreview_surfaceView);
        cameraPreviewFrameView.setListener(this);

//        WatermarkSetting watermarksetting = new WatermarkSetting(this);
//        watermarksetting.setResourceId(R.mipmap.ic_launcher)
//                .setAlpha(100)
//                .setSize(WatermarkSetting.WATERMARK_SIZE.MEDIUM)
//                .setCustomPosition(0.5f, 0.5f);

        mMediaStreamingManager = new MediaStreamingManager(this, afl, cameraPreviewFrameView,
                AVCodecType.SW_VIDEO_WITH_SW_AUDIO_CODEC); // sw codec

//        mMediaStreamingManager.prepare(mCameraStreamingSetting, mMicrophoneStreamingSetting, watermarksetting, mProfile);
        mMediaStreamingManager.prepare(mCameraStreamingSetting, mMicrophoneStreamingSetting, mProfile);

        mMediaStreamingManager.setStreamingStateListener(this);
        mMediaStreamingManager.setSurfaceTextureCallback(this);
        mMediaStreamingManager.setStreamingSessionListener(this);
//        mMediaStreamingManager.setNativeLoggingEnabled(false);
        mMediaStreamingManager.setStreamStatusCallback(this);
        mMediaStreamingManager.setStreamingPreviewCallback(this);
        mMediaStreamingManager.setAudioSourceCallback(this);
        //setFocusAreaIndicator();
        initview();
    }

    @Override
    public boolean onRecordAudioFailedHandled(int err) {
        mMediaStreamingManager.updateEncodingType(AVCodecType.SW_VIDEO_CODEC);
        mMediaStreamingManager.startStreaming();
        return true;
    }

    private void initview() {
        status_top = (LinearLayout) findViewById(R.id.status_top);
        camera_button_holder = (RelativeLayout) findViewById(R.id.camera_button_holder);
        myRecycler = (MyRecyclerView) findViewById(R.id.myRecycler);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        lp.setMargins(0, getStatusBarHeight(), 0, 0);
        status_top.setLayoutParams(lp);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        myRecycler.setLayoutManager(linearLayoutManager);
        recyclerAdapter = new PropertyImgAdapter(this, getData());
        myRecycler.setAdapter(recyclerAdapter);
        recyclerAdapter.setOnItemClickLitener(new PropertyImgAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                Pop(getData().get(position), (position % 2) == 1 ? 1 : -1);
            }
        });

        image1 = (ImageView) findViewById(R.id.image1);
        image3 = (ImageView) findViewById(R.id.image3);
        image4 = (ImageView) findViewById(R.id.image4);
        image5 = (ImageView) findViewById(R.id.image5);
        image1.setOnClickListener(this);
        image3.setOnClickListener(this);
        image4.setOnClickListener(this);
        image5.setOnClickListener(this);


        relativeLayout1 = (RelativeLayout) findViewById(R.id.relativeLayout1);
        relativeLayout2 = (RelativeLayout) findViewById(R.id.relativeLayout2);
        camera_button_holder = (RelativeLayout) findViewById(R.id.camera_button_holder);
        LayoutTransition transition = new LayoutTransition();
        camera_button_holder.setLayoutTransition(transition);

        edit_querys = (EditText) findViewById(R.id.edit_querys);
        streaming_list = (ListView) findViewById(R.id.streaming_list);
        newsAdapter = new NewsAdapter(CameraStreamingActivity.this);
        streaming_list.setAdapter(newsAdapter);
    }

    public List<String> getData() {
        List<String> list = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            list.add("item" + i);
        }
        return list;
    }

    /**
     * 获取手机状态栏高度
     *
     * @return
     */
    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }


    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.image1:
                relativeLayout1.setVisibility(View.GONE);
                relativeLayout2.setVisibility(View.VISIBLE);
                edit_querys.setFocusable(true);
                edit_querys.setFocusableInTouchMode(true);
                edit_querys.requestFocus();
                edit_querys.setImeOptions(EditorInfo.IME_ACTION_SEND);
                InputMethodManager inputManager = (InputMethodManager) edit_querys.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManager.showSoftInput(edit_querys, 0);
                camera_button_holder.getViewTreeObserver().addOnGlobalLayoutListener(onGlobal);
                break;
            case R.id.image3:
                intent = new Intent(CameraStreamingActivity.this, EndActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.image4:
                hideKeyboard(edit_querys);
                relativeLayout1.setVisibility(View.VISIBLE);
                relativeLayout2.setVisibility(View.GONE);
                break;
            case R.id.image5:
                News news = new News();
                news.name = "me";
                news.content = edit_querys.getText().toString();
                news.type = 1;
                newList.add(news);
                newsAdapter.clear();
                newsAdapter.addItem(newList);
                newsAdapter.notifyDataSetChanged();
                edit_querys.setText("");
                break;
            case R.id.pop_quit:
                if (dialog != null) dialog.dismiss();
                break;
            case R.id.pop_btn1:
                Dia("确定要将此人踢出房间?");
                break;
            case R.id.pop_btn2:
                Dia("确定要将此人禁言?");
                break;
            case R.id.dia_btn1:
                if (dialog2 != null) dialog2.dismiss();
                break;
            case R.id.dia_btn2:
                if (dialog2 != null) dialog2.dismiss();
                if (dialog != null) dialog.dismiss();
                break;
        }
    }

    private void Pop(String name, int type) {
        dialog = new Dialog(CameraStreamingActivity.this, R.style.mystyle);
        View contentView = LayoutInflater.from(CameraStreamingActivity.this).inflate(R.layout.poplayout, null);
        pop_quit = (ImageView) contentView.findViewById(R.id.pop_quit);
        pop_name = (TextView) contentView.findViewById(R.id.pop_name);
        pop_img = (CircleImageView) contentView.findViewById(R.id.pop_img);
        pop_btn1 = (TextView) contentView.findViewById(R.id.pop_btn1);
        pop_btn2 = (TextView) contentView.findViewById(R.id.pop_btn2);
        pop_name.setText(name);
        if (type == -1) {
            pop_btn2.setText("取消禁言");
        } else {
            pop_btn2.setText("禁言");
        }
        pop_quit.setOnClickListener(this);
        pop_btn1.setOnClickListener(this);
        pop_btn2.setOnClickListener(this);
        dialog.setContentView(contentView);
        dialog.show();
    }

    private void Dia(String content) {
        dialog2 = new Dialog(CameraStreamingActivity.this, R.style.mystyle2);
        View contentView = LayoutInflater.from(CameraStreamingActivity.this).inflate(R.layout.dialayout, null);
        dia_name = (TextView) contentView.findViewById(R.id.dia_name);
        dia_btn1 = (TextView) contentView.findViewById(R.id.dia_btn1);
        dia_btn2 = (TextView) contentView.findViewById(R.id.dia_btn2);
        dia_name.setText(content);
        dia_btn1.setOnClickListener(this);
        dia_btn2.setOnClickListener(this);
        dialog2.setContentView(contentView);
        dialog2.show();
    }

    /**
     * 隐藏键盘
     *
     * @param view
     */
    public void hideKeyboard(View view) {
        InputMethodManager manager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        if (manager.isActive()) {
            manager.hideSoftInputFromWindow(view.getApplicationWindowToken(), 0);
        }
        camera_button_holder.setPadding(0, 0, 0, 0);
    }

    int a = 0;
    int b = 0;
    ViewTreeObserver.OnGlobalLayoutListener onGlobal = new ViewTreeObserver.OnGlobalLayoutListener() {
        @Override
        public void onGlobalLayout() {
            Rect r = new Rect();
            camera_button_holder.getWindowVisibleDisplayFrame(r);
            int screenHeight = camera_button_holder.getRootView().getHeight();
            int heightDifference = screenHeight - (r.bottom - r.top);
            if (a == 0 && b == 0) {
                a = b = heightDifference;
            } else {
                if (a != heightDifference) {
                    b = heightDifference;
                }
            }

            if (a > b) {
                int c = a;
                a = b;
                b = c;
            }

            Log.e("Keyboard Size", "Size1:" + heightDifference + "," + a + "," + b + "," + (heightDifference - a));
            camera_button_holder.setPadding(0, 0, 0, (heightDifference - a));
        }
    };

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN
                && KeyEvent.KEYCODE_BACK == keyCode) {
            startActivity(new Intent(CameraStreamingActivity.this, EndActivity.class));
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
