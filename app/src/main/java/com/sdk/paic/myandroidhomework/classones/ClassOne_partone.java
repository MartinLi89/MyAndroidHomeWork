package com.sdk.paic.myandroidhomework.classones;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.sdk.paic.myandroidhomework.BaseActivity;
import com.sdk.paic.myandroidhomework.R;
import com.sdk.paic.myandroidhomework.entities.UserBean;
import com.sdk.paic.myandroidhomework.greendaoutils.MyDbHelper;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ClassOne_partone extends BaseActivity {

    @BindView(R.id.btn_insert)
    Button mBtnInsert;
    @BindView(R.id.btn_update)
    Button mBtnUpdate;
    @BindView(R.id.btn_delete)
    Button mBtnDelete;
    @BindView(R.id.btn_query)
    Button mBtnQuery;

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
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_insert, R.id.btn_update, R.id.btn_delete, R.id.btn_query})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_insert:
                MyDbHelper instance = MyDbHelper.getInstance();

                for (long i = 0; i < 10; i++) {
                    UserBean userBean = new UserBean( i + "", "d", "", "");
                    instance.insert(userBean);
                }


                break;
            case R.id.btn_update:
                MyDbHelper btn_update = MyDbHelper.getInstance();
                UserBean userBeana = new UserBean( "1", "10", "", "");
                btn_update.update(userBeana);
                break;
            case R.id.btn_delete:
                MyDbHelper btn_delete = MyDbHelper.getInstance();
                btn_delete.delete( new UserBean( "1", "10", "", ""));
                break;
            case R.id.btn_query:
                MyDbHelper instancess = MyDbHelper.getInstance();
                List<UserBean> check = instancess.check();

                Toast.makeText(ClassOne_partone.this, check.get(0).getName(), Toast.LENGTH_SHORT).show();
                for (UserBean userBean : check) {
                    Log.e("userBean", userBean.getName());
                }
                break;
            default:
                break;
        }
    }
}
