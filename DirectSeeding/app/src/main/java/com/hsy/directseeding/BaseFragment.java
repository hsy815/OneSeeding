package com.hsy.directseeding;

import android.support.v4.app.Fragment;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;

/**
 * 作者:hsy
 * 16/9/28 17:28
 * 注释:
 */
public class BaseFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener, View.OnTouchListener {

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }
}
