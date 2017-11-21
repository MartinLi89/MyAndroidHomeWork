package com.sdk.paic.myandroidhomework.classones;

import android.os.Bundle;
import android.widget.Toast;

import com.sdk.paic.myandroidhomework.BaseActivity;
import com.sdk.paic.myandroidhomework.R;

public class ClassOne_partone extends BaseActivity {


	@Override
	protected void initView() {
	}

	@Override
	public int getContentView() {
		return R.layout.activity_class_one_partone;
	}


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}




	public void showToast(String msg) {
		Toast.makeText(ClassOne_partone.this, msg, Toast.LENGTH_SHORT).show();
	}


}
