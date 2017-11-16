package com.sdk.paic.myandroidhomework.greendaoutils;

import com.sdk.paic.myandroidhomework.MyApplication;
import com.sdk.paic.myandroidhomework.entities.UserBean;

import org.greenrobot.greendao.query.Query;
import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author ex-lisuyang001
 * @date 2017/11/16
 */

public class MyDbHelper {


	private static AtomicReference<MyDbHelper> INSTANCE = new AtomicReference<>();
	private UserBeanDao userBeanDao;

	public MyDbHelper() {
		initUserBeanDao();
	}

	private String DBNAME = "MyDB.db";
	private String MY_KEY = "fdfsdf123";

	private void initUserBeanDao() {
		DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(MyApplication.mContext, DBNAME, null);
		DaoMaster daoMaster = new DaoMaster(devOpenHelper.getEncryptedWritableDb(MY_KEY));
		userBeanDao = daoMaster.newSession().getUserBeanDao();
	}

	public static MyDbHelper getInstance() {
		MyDbHelper temp = INSTANCE.get();
		for (; ; ) {
			if (temp != null) {
				return temp;
			}

			temp = new MyDbHelper();

			if (INSTANCE.compareAndSet(null, temp)) {
				return temp;
			}

		}
	}

	public long insert(UserBean userBean) {
		long insert = userBeanDao.insert(userBean);
		String sabdfffdsfs = "";
		return insert;

	}

	public void update(UserBean userBeana) {
//		"张三", "100", "100kg", "abc"
		Query<UserBean> build =
				userBeanDao.queryBuilder().where(UserBeanDao.Properties.Name.eq("张三"))
//						.or(UserBeanDao.Properties.Name.eq("100"));
//						.and(UserBeanDao.Properties.Abc.eq("abc"))
//						.and(UserBeanDao.Properties.Weight.eq("100kg"))
						.build();
//		build.setParameter()
//		build.list();
		UserBean unique = build.unique();

//		String adb = unique.getName();

	}

	public void delete(UserBean userBean) {

	}

	public List<UserBean> check() {
		QueryBuilder<UserBean> builder = userBeanDao.queryBuilder();//.orderCustom();
//		builder.where(UserBeanDao.Properties.Name)

		List<UserBean> list = builder.list();

//		Query<UserBean> build =
//				userBeanDao.queryBuilder().where(UserBeanDao.Properties.Name.eq("1")).build();
//		List<UserBean> list = build.list();
		return list;
	}
}
