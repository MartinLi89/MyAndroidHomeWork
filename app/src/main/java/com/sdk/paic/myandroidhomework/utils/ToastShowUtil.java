package com.sdk.paic.myandroidhomework.utils;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.sdk.paic.myandroidhomework.R;


public class ToastShowUtil {


    private static Toast mToast;
    private static TextView mTextview;

    public static void showToast(Context context, String tip) {

        initToast(context);
        mTextview.setText(tip);
        mToast.show();



    }

    private static void initToast(Context context) {
        if (mToast == null) {
            synchronized (ToastShowUtil.class) {
                if (mToast == null) {
                    mToast = new Toast(context.getApplicationContext());
                    mToast.setDuration(Toast.LENGTH_SHORT);
                    View view = LayoutInflater.from(context).inflate(R.layout.layout_toast_show_util, null);
                    mTextview = (TextView) view.findViewById(R.id.toast_tv);
                    mToast.setView(view);
                    mToast.setGravity(Gravity.CENTER, 0, 0);
                }
            }
        }
    }


}
