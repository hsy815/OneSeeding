package com.hsy.directseeding.activity;

import android.animation.LayoutTransition;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hsy.directseeding.R;
import com.hsy.directseeding.adapter.NewsAdapter;
import com.hsy.directseeding.adapter.PropertyImgAdapter;
import com.hsy.directseeding.entity.News;
import com.hsy.directseeding.uitl.Constant;
import com.hsy.directseeding.view.CameraPreviewFrameView;
import com.hsy.directseeding.view.CircleImageView;
import com.hsy.directseeding.view.MyRecyclerView;
import com.maxleap.im.DataHandler;
import com.maxleap.im.ParrotException;
import com.maxleap.im.SimpleDataHandler;
import com.maxleap.im.entity.Message;
import com.maxleap.im.entity.MessageBuilder;
import com.maxleap.im.entity.Room;
import com.qiniu.pili.droid.streaming.AVCodecType;
import com.qiniu.pili.droid.streaming.MediaStreamingManager;
import com.qiniu.pili.droid.streaming.widget.AspectFrameLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.hsy.directseeding.MyApplication.parrot;

public class CameraStreamingActivity extends StreamingBaseActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    private LinearLayout status_top;
    private MyRecyclerView myRecycler;
    private PropertyImgAdapter recyclerAdapter;
    private RelativeLayout image1;
    private RelativeLayout image3;
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
    List<String> listMembers = new ArrayList<>();
    private Room rooms;

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        overridePendingTransition(R.anim.anim_activity_right_in, R.anim.anim_activity_left_out);
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode, Bundle options) {
        super.startActivityForResult(intent, requestCode, options);
        overridePendingTransition(R.anim.anim_activity_right_in, R.anim.anim_activity_left_out);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //透明状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        cameraSeting();
        initview();
//        StartRoom();
        rooms = new Room();
        rooms.setId("f396bbf3bf764c9783788d56080d12bf");
        addRoom("hsys");

    }

    @Override
    public boolean onRecordAudioFailedHandled(int err) {
        mMediaStreamingManager.updateEncodingType(AVCodecType.SW_VIDEO_CODEC);
        mMediaStreamingManager.startStreaming();
        return true;
    }

    /**
     * 视频处理
     */
    private void cameraSeting() {

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
    }

    /**
     * 视图
     */
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

            }
        });

        image1 = (RelativeLayout) findViewById(R.id.image1);
        image3 = (RelativeLayout) findViewById(R.id.image3);
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
        streaming_list.setOnItemClickListener(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        getMessage();
      /*  if (rooms != null) {
            parrot.updateRoom(rooms, new DataHandler<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    Log.e("rooms", "聊天室更新成功");
                }

                @Override
                public void onError(ParrotException e) {
                }
            });
        } else {
            Log.e("rooms", "聊天室更新失败");
            StartRoom();
        }*/
    }

    /**
     * 创建聊天室
     */
    public void StartRoom() {
        parrot.createRoom("啦啦",
                Arrays.asList("hsy"),//Variable.CLIENT_KEY
                new DataHandler<Room>() {
                    @Override
                    public void onSuccess(Room room) {
                        rooms = room;
                        Log.e("rooms", "聊天室创建成功" + rooms.getName() + "聊天室成员数" + rooms.getMembers().size() + "聊天室成员一" + rooms.getMembers().get(0));
                    }

                    @Override
                    public void onError(ParrotException e) {
                        Log.e("rooms", "聊天室创建失败" + e);
                    }

                });
    }

    /**
     * 发送消息到聊天室
     *
     * @param message
     */
    public void sendMessage(String message) {
        Message msg = MessageBuilder.newBuilder()
                .toRoom(rooms.getId()) // 目标的 Room 的 Id
                .text(message);

        Log.e("rooms", "发送消息了" + rooms.getId());
        parrot.sendMessage(msg, new SimpleDataHandler<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.e("rooms", "发送消息成功");
                Constant.toast_s("sendMessage() success");
            }

        });

//        Log.e("rooms", "聊天室创建成功" + rooms.getName() + "聊天室成员数" + rooms.getMembers().size() + "聊天室成员一" + rooms.getMembers().get(0));
    }

    /**
     * 发送系统消息
     *
     * @param targetFriend
     * @param content
     */
    public void sendSystemMessage(String targetFriend, String content) {
        Message msg = MessageBuilder.newBuilder()
                .toRoom(targetFriend)
                .text(content);
        Log.e("rooms", "发送系统消息" + rooms.getId());
        parrot.sendSystemMessage(targetFriend, msg, new DataHandler<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.e("rooms", "发送系统消息成功");
            }

            @Override
            public void onError(ParrotException e) {
            }
        });
    }

    /**
     * 获取聊天室消息
     */
    public void getMessage() {
        parrot.onMessage(new SimpleDataHandler<Message>() {
            @Override
            public void onSuccess(Message message) {
                if (message.getFrom().fromRoom()) {
                    Log.e("rooms", "取到聊天时消息");
                    News news = new News();
                    news.name = message.getFrom().getId();
                    news.content = message.getContent().getBody().toString();
                    news.type = 1;
                    newList.add(news);
                    newsAdapter.clear();
                    newsAdapter.addItem(newList);
                    newsAdapter.notifyDataSetChanged();
                }
            }
        });

        parrot.onSystemMessage(new SimpleDataHandler<Message>() {
            @Override
            public void onSuccess(Message message) {
                Log.e("rooms", "取到系统消息" + message.getContent().toString());
                News news = new News();
                news.name = "系统消息";
                news.content = message.getContent().getBody().toString();
                news.type = 2;
                newList.add(news);
                newsAdapter.clear();
                newsAdapter.addItem(newList);
                newsAdapter.notifyDataSetChanged();
            }
        });
    }

    /**
     * 添加成员
     *
     * @param Members
     */
    private void addRoom(String Members) {
        listMembers.clear();
        listMembers.add(Members);
        parrot.addRoomMembers(rooms.getId(), listMembers, new DataHandler<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.e("rooms", "添加成员成功");
            }

            @Override
            public void onError(ParrotException e) {
                Log.e("rooms", "添加成员失败" + e);
            }
        });
    }

    /**
     * 移除成员
     *
     * @param removedMembers
     */
    private void removeRoom(List<String> removedMembers) {
        parrot.removeRoomMembers(rooms.getId(), removedMembers, new DataHandler<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.e("rooms", "移除成员成功");
            }

            @Override
            public void onError(ParrotException e) {
                Log.e("rooms", "移除成员失败" + e);
            }
        });
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
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        News news = (News) newsAdapter.getItem(position);
        Pop(news.name, 1);
    }

    private int popORdia = 0;

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
                /*News news = new News();
                news.name = "me";
                news.content = edit_querys.getText().toString();
                news.type = 1;
                newList.add(news);
                newsAdapter.clear();
                newsAdapter.addItem(newList);
                newsAdapter.notifyDataSetChanged();*/
//                sendMessage(edit_querys.getText().toString());
                sendSystemMessage(rooms.getId(), edit_querys.getText().toString() + "被禁言");
                edit_querys.setText("");
                break;
            case R.id.pop_quit:
                if (dialog != null) dialog.dismiss();
                break;
            case R.id.pop_btn1:
                Dia("确定要将此人踢出房间?");
                popORdia = 1;
                break;
            case R.id.pop_btn2:
                Dia("确定要将此人禁言?");
                popORdia = 2;
                break;
            case R.id.dia_btn1:
                if (dialog2 != null) dialog2.dismiss();
                break;
            case R.id.dia_btn2:
                if (dialog2 != null) dialog2.dismiss();
                if (dialog != null) dialog.dismiss();
                if (popORdia == 1) {
                    //踢人
                    popORdia = 0;

                } else if (popORdia == 2) {
                    //禁言
                    popORdia = 0;
//                    List<String> list = new ArrayList<>();
//                    list.add(pop_name.getText().toString());
//                    removeRoom(list);
                    sendSystemMessage(rooms.getId(), pop_name.getText().toString() + "被禁言");
                }
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
    protected void onPause() {
        super.onPause();
        parrot.offMessage(new SimpleDataHandler<Message>() {
            @Override
            public void onSuccess(Message message) {

            }
        });
        parrot.offSystemMessage(new SimpleDataHandler<Message>() {
            @Override
            public void onSuccess(Message message) {

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (rooms != null) {
            removeRoom(listMembers);
            parrot.deleteRoom(rooms.getId(), new DataHandler<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    Log.e("rooms", "聊天室销毁");
                }

                @Override
                public void onError(ParrotException e) {
                }
            });
        }
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.anim_activity_left_in, R.anim.anim_activity_right_out);
    }

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

    public List<String> getData() {
        List<String> list = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            list.add("item" + i);
        }
        return list;
    }

}
