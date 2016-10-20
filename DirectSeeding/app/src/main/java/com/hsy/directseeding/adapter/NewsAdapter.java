package com.hsy.directseeding.adapter;/**
 * Created by hsy on 16/10/9.
 */

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hsy.directseeding.R;
import com.hsy.directseeding.emoji.EmojiMap;
import com.hsy.directseeding.entity.News;

/**
 * 类名: NewsAdapter
 * Created by hsy on 16/10/9.
 */
public class NewsAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater inflater;

    public NewsAdapter(Context context) {
        super();
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        HolderView holderView = null;
        if (convertView == null) {
            holderView = new HolderView();
            convertView = inflater.inflate(R.layout.item_news, parent, false);
            holderView.news_name = (TextView) convertView.findViewById(R.id.news_name);
            holderView.news_content = (TextView) convertView.findViewById(R.id.news_content);
            convertView.setTag(holderView);
        } else {
            holderView = (HolderView) convertView.getTag();
        }

        News news = (News) getItem(position);
        if (news.type == 1) {
            String contents = news.name + ": " + news.content;
            SpannableString msp = new SpannableString(contents);
            msp.setSpan(new ForegroundColorSpan(ContextCompat.getColor(context, R.color.colorAccent)), 0, (news.name.length() + 1), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);  //设置前景色
            EmojiMap.getInstance().setText(holderView.news_name,msp);
        }else if(news.type ==2){
            String contents = news.name + ": " + news.content;
            SpannableString msp = new SpannableString(contents);
            msp.setSpan(new ForegroundColorSpan(ContextCompat.getColor(context, R.color.end_text_blue)), 0, (news.name.length() + 1), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);  //设置前景色
            EmojiMap.getInstance().setText(holderView.news_name,msp);
        }else{
            String contents = news.name + ": " + news.content;
            EmojiMap.setText(holderView.news_name,contents);
        }
        return convertView;
    }

    public class HolderView {
        private TextView news_name;
        private TextView news_content;
    }
}
