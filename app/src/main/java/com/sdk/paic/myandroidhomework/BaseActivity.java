package com.sdk.paic.myandroidhomework;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author ex-lisuyang001
 * @date 2017/11/7
 */

public abstract class BaseActivity extends AppCompatActivity {

	protected Unbinder bind;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(getContentView());

		bind = ButterKnife.bind(this);

		initView();
	}

	/**
	 * 初始化控件
	 */
	protected abstract void initView();


	/**
	 * 加载布局
	 *
	 * @return
	 */
	public abstract int getContentView();


	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (bind != null) {
			bind.unbind();
			bind = null;

		}


	}
}
