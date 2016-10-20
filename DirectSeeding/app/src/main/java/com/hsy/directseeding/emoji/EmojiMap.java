/**
 * Copyright (c) 2015-present, MaxLeapMobile.
 * All rights reserved.
 * ----
 * This source code is licensed under the BSD-style license found in the
 * LICENSE file in the root directory of this source tree. An additional grant
 * of patent rights can be found in the PATENTS file in the same directory.
 */
package com.hsy.directseeding.emoji;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ImageSpan;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.hsy.directseeding.MyApplication;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmojiMap {

    private HashMap<String, Integer> emoMap;
    private HashMap<String, Bitmap> picMap;
    private ArrayList<String> emojiNames;
    private static EmojiMap emojiMap;
    private Context mContext;

    public static EmojiMap getInstance() {
        if (emojiMap == null) {
            synchronized (EmojiMap.class) {
                if (emojiMap == null) {
                    emojiMap = new EmojiMap();
                }
            }
        }
        emojiMap.init(MyApplication.context);
        return emojiMap;
    }

    private EmojiMap() {

    }


    private void init(Context context) {
        mContext = context;
        if (emoMap == null || picMap == null || emojiNames == null) {
            emoMap = new HashMap<>();
            picMap = new HashMap<>();
            emojiNames = new ArrayList<>();
        } else {
            return;
        }
        StringBuffer buffer = new StringBuffer();
        try {
            String[] emojis = context.getResources().getAssets().list("emoji");
            for (String name : emojis) {
                String noSuffixName = name.substring(0, name.length() - 4);
                emojiNames.add("[" + noSuffixName + "]");
                if (name != emojis[emojis.length - 1]) {
                    emoMap.put("[" + noSuffixName + "]", Integer.parseInt(noSuffixName, 16));
                }
//                Character.toChars(Integer.parseInt(noSuffixName, 16));
                InputStream open = null;
                try {
                    open = context.getAssets().open("emoji/" + name);
                    picMap.put("[" + noSuffixName + "]", BitmapFactory.decodeStream(open));
                } catch (Exception e) {
                    Log.d("","read emoji file failed");
                } finally {
                    open.close();
                }
            }
        } catch (Exception e) {
            Log.d("","read emoji file failed");
        }
    }

    public ArrayList<String> getEmojiNames() {
        return emojiNames;
    }

    public Bitmap getBitmap(String key) {
        return picMap.get(key);
    }

    public static void addText(EditText editText, String text) {
        String viewTxt = editText.getText().toString();
        Editable editable = editText.getText().append(text);
//        int size = (int) editText.getTextSize() * 13 / 10;
        Bitmap scaleBitmap = emojiMap.getBitmap(text);
        if (scaleBitmap != null) {
//            scaleBitmap = Bitmap.createScaledBitmap(scaleBitmap, size, size, true);
            ImageSpan span = new ImageSpan(emojiMap.mContext, scaleBitmap);
            editable.setSpan(span, viewTxt.length(), viewTxt.length() + text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        editText.setText(editable);
        editText.setSelection(editable.length());
    }

    public static void delText(EditText editText) {
        if (TextUtils.isEmpty(editText.getText())) return;
        String viewTxt = editText.getText().toString();
        Editable editable = editText.getText();
        ImageSpan[] spans = editable.getSpans(0, viewTxt.length(), ImageSpan.class);
        if (spans != null && spans.length > 0 && editable.getSpanEnd(spans[spans.length - 1]) == viewTxt.length()) {
            int start = editable.getSpanStart(spans[spans.length - 1]);
            editable.delete(start, viewTxt.length());
        } else {
            editable.delete(viewTxt.length() - 1, viewTxt.length());
        }
        editText.setText(editable);
        editText.setSelection(editable.length());
    }

    public static void setText(TextView textView, String text) {
        if (TextUtils.isEmpty(text)) {
            textView.setText("");
            return;
        }
        String regexEmotion = "\\[[0-9a-z]{4,5}\\]";
        Pattern patternEmotion = Pattern.compile(regexEmotion);
        Matcher matcherEmotion = patternEmotion.matcher(text);
        SpannableString spannableString = new SpannableString(text);
        while (matcherEmotion.find()) {
            String key = matcherEmotion.group();
            int start = matcherEmotion.start();
            Bitmap scaleBitmap = emojiMap.getBitmap(key);
            if (scaleBitmap != null) {
                ImageSpan span = new ImageSpan(emojiMap.mContext, scaleBitmap);
                spannableString.setSpan(span, start, start + key.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
        }
        textView.setText(spannableString);
    }

    public static void setText(TextView textView, SpannableString text) {
        if (TextUtils.isEmpty(text)) {
            textView.setText("");
            return;
        }
        String regexEmotion = "\\[[0-9a-z]{4,5}\\]";
        Pattern patternEmotion = Pattern.compile(regexEmotion);
        Matcher matcherEmotion = patternEmotion.matcher(text);
        SpannableString spannableString = new SpannableString(text);
        while (matcherEmotion.find()) {
            String key = matcherEmotion.group();
            int start = matcherEmotion.start();
            Bitmap scaleBitmap = emojiMap.getBitmap(key);
            if (scaleBitmap != null) {
                ImageSpan span = new ImageSpan(emojiMap.mContext, scaleBitmap);
                spannableString.setSpan(span, start, start + key.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
        }
        textView.setText(spannableString);
    }

}
