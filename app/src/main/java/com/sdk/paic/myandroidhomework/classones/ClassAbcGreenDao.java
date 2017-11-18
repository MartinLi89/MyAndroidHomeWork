package com.sdk.paic.myandroidhomework.classones;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.sdk.paic.myandroidhomework.R;
import com.sdk.paic.myandroidhomework.entities.UserBean;
import com.sdk.paic.myandroidhomework.greendaoutils.MyDbHelper;
import com.sdk.paic.myandroidhomework.utils.CrmGetDbtoSdcard;
import com.sdk.paic.myandroidhomework.utils.ToastShowUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ClassAbcGreenDao extends AppCompatActivity
		implements NavigationView.OnNavigationItemSelectedListener {
	@BindView(R.id.btn_insert)
	Button mBtnInsert;
	@BindView(R.id.btn_update)
	Button mBtnUpdate;
	@BindView(R.id.btn_delete)
	Button mBtnDelete;
	@BindView(R.id.btn_query)
	Button mBtnQuery;
	@BindView(R.id.btn_control)
	Button btnControl;

	Context mContext;
	@BindView(R.id.et_name)
	EditText etName;
	@BindView(R.id.et_age)
	EditText etAge;
	@BindView(R.id.fab)
	FloatingActionButton fab;
	@BindView(R.id.nav_view)
	NavigationView navView;
	@BindView(R.id.drawer_layout)
	DrawerLayout drawerLayout;
	@BindView(R.id.et_weight)
	EditText etWeight;
	@BindView(R.id.et_abc)
	EditText etAbc;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_class_abc_green_dao);
		ButterKnife.bind(this);
		mContext = this;
		initUI();
	}

	@OnClick({R.id.btn_insert, R.id.btn_update, R.id.btn_delete, R.id.btn_query, R.id.btn_control})
	public void onViewClicked(View view) {
		String name = etName.getText().toString().trim();
		String age = etAge.getText().toString().trim();
		String weight = etWeight.toString().trim();
		String abc = etAbc.toString().trim();
		switch (view.getId()) {
			case R.id.btn_insert:
				MyDbHelper instance = MyDbHelper.getInstance();

				UserBean userBean = new UserBean(name, age, weight, abc);
				showToast(instance.insert(userBean) + "");


				break;
			case R.id.btn_update:
				MyDbHelper btn_update = MyDbHelper.getInstance();
				UserBean userBeana = new UserBean(name, age, weight, abc);
				btn_update.update(userBeana);
				break;
			case R.id.btn_delete:
				MyDbHelper btn_delete = MyDbHelper.getInstance();
				btn_delete.delete(new UserBean(name, age, weight, abc));
				deleteAll();
				break;
			case R.id.btn_query:
				queryOne(name, age, weight, abc);
				queryCount(name);
				break;
			case R.id.btn_control:
				CrmGetDbtoSdcard.copyDBToSdcard(mContext, "MyDB.db");
				break;
			default:
				break;
		}
	}

	private void queryCount(String name) {
		long l = MyDbHelper.getInstance().checkCount(name);
		showToast(l + "");
	}

	private void queryOne(String name, String age, String weight, String abc) {
		MyDbHelper instancess = MyDbHelper.getInstance();
		List<UserBean> check = instancess.checkOne(name, age, weight, abc);

		for (UserBean userBeanaa : check) {
			showToast(userBeanaa.getName() + "");
		}
	}

	private void deleteAll() {
		MyDbHelper instancess = MyDbHelper.getInstance();
		instancess.deleteAll();

	}


	public void showToast(String msg) {
		Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();

//		ToastShowUtil.showToast(mContext, msg);
	}


	private void initUI() {
		ActionBar supportActionBar = getSupportActionBar();
		if (supportActionBar != null) {
			ToastShowUtil.showToast(mContext, "ActionBar");
		}
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
		fab.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
						.setAction("Action", null).show();
			}
		});

		DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
		ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
				this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
		drawer.addDrawerListener(toggle);
		toggle.syncState();

		NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
		navigationView.setNavigationItemSelectedListener(this);
	}

	@Override
	public void onBackPressed() {
		DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
		if (drawer.isDrawerOpen(GravityCompat.START)) {
			drawer.closeDrawer(GravityCompat.START);
		} else {
			super.onBackPressed();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.class_abc_green_dao, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		//noinspection SimplifiableIfStatement
		if (id == R.id.action_settings) {
			return true;
		}

		return super.onOptionsItemSelected(item);
	}

	@SuppressWarnings("StatementWithEmptyBody")
	@Override
	public boolean onNavigationItemSelected(MenuItem item) {
		// Handle navigation view item clicks here.
		int id = item.getItemId();

		if (id == R.id.nav_camera) {
			// Handle the camera action
		} else if (id == R.id.nav_gallery) {

		} else if (id == R.id.nav_slideshow) {

		} else if (id == R.id.nav_manage) {

		} else if (id == R.id.nav_share) {

		} else if (id == R.id.nav_send) {

		}

		DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
		drawer.closeDrawer(GravityCompat.START);
		return true;
	}
}
