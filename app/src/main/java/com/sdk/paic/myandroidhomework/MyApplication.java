package com.sdk.paic.myandroidhomework;

import android.app.Application;
import android.content.Context;

/**
 * @author ex-lisuyang001
 * @date 2017/11/16
 */

public class MyApplication extends Application {

	public static Context mContext;

	@Override
	public void onCreate() {
		super.onCreate();
		mContext = this;
	}
}
