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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.hsy.directseeding.R;

import java.util.ArrayList;

public class EmojiViewAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<String> mList;
    private static final int PAGE_SIZE = 20;

    public EmojiViewAdapter(Context context, ArrayList<String> list, int page) {
        mContext = context;
        mList = new ArrayList<>();
        int start = page * PAGE_SIZE;
        int end = start + PAGE_SIZE;
        for (int i = start; i < end; i++) {
            if (i < list.size()) {
                mList.add(list.get(i));
            }
        }
        mList.add(list.get(list.size() - 1));
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return position == PAGE_SIZE ? null : mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.mcommon_item_emoji, parent, false);
            holder = new ViewHolder();
            holder.emoji = (ImageView) convertView;
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.emoji.setImageBitmap(EmojiMap.getInstance().getBitmap(mList.get(position)));

        return convertView;
    }

    static class ViewHolder {
        ImageView emoji;
    }
}
