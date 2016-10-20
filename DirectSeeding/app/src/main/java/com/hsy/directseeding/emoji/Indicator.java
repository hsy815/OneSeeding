package com.hsy.directseeding.emoji;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.hsy.directseeding.R;

public class Indicator extends LinearLayout {
    private int mCount;
    private int mBackgroundResource = R.drawable.ic_indicator;

    public Indicator(Context context) {
        super(context);
    }

    public Indicator(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setBackgroundResource(int res) {
        mBackgroundResource = res;
    }

    public void setCount(int count) {
        mCount = count;
        LayoutParams lp = new LayoutParams(LayoutParams.WRAP_CONTENT
                , LayoutParams.WRAP_CONTENT);
        lp.setMargins(EmoticonsKeyboardUtils.dip2px(getContext(), 3), 0, 0, 0);
        this.removeAllViews();
        for (int i = 0; i < mCount; i++) {
            ImageView imageView = new ImageView(getContext());
            imageView.setBackgroundResource(mBackgroundResource);
            this.addView(imageView, lp);
        }

    }

    public void select(int position) {
        for (int i = 0; i < mCount; i++) {
            this.getChildAt(i).setSelected(false);
        }
        if (this.getChildAt(position) != null) {
            this.getChildAt(position).setSelected(true);
        }
    }

}
