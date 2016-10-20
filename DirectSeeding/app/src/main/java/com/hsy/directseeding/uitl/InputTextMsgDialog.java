package com.hsy.directseeding.uitl;/**
 * Created by hsy on 16/10/17.
 */

import android.app.Dialog;
import android.content.Context;
import android.view.WindowManager;

import com.hsy.directseeding.R;

/**
 * 类名: InputTextMsgDialog
 * Created by hsy on 16/10/17.
 */
public class InputTextMsgDialog extends Dialog {

    public InputTextMsgDialog(Context context) {
        super(context, R.style.live_room_dialog);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE |
                WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        setContentView(R.layout.mlive_dialog_input);
    }

}
