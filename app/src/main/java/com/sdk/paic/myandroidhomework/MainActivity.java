package com.sdk.paic.myandroidhomework;


import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.sdk.paic.myandroidhomework.classones.ClassOne_partone;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * @author ex-lisuyang001
 */
public class MainActivity extends BaseActivity {

	@BindView(R.id.lv_show_selector)
	RecyclerView rlistView;

	private String[] selectors = new String[]{
			"第一课",
			"第二课",
			"第二课",
			"第二课",
			"第二课",
			"第二课",
			"第二课",
			"第二课",
			"第二课",
			"第二课",
			"第二课",
			"第二课",
			"第二课",
			"第二课",
			"第二课",
			"第二课",
			"第二课",
			"第三课"
	};
	private ArrayList<String> mDatas = new ArrayList<>();
	private ListViewAdapter listViewAdapter;

	{
		for (String selector : selectors) {
			mDatas.add(selector);
		}
	}

	@Override
	protected void initView() {

		listViewAdapter = new ListViewAdapter(mDatas);
		rlistView.setAdapter(listViewAdapter);
		rlistView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
		rlistView.setHasFixedSize(true);

	}

	public void onitemClick(View view) {
		int childAdapterPosition = rlistView.getChildAdapterPosition(view);
		Toast.makeText(MainActivity.this, "点击了第" + childAdapterPosition + "个", Toast.LENGTH_SHORT).show();
		if (childAdapterPosition == 0) {
			startActivity(new Intent(this, ClassOne_partone.class));
		}

	}

	@Override
	public int getContentView() {

		return R.layout.activity_main;
	}
}
