package com.sdk.paic.myandroidhomework.greendaoutils;

import android.text.style.BulletSpan;

import com.sdk.paic.myandroidhomework.MyApplication;
import com.sdk.paic.myandroidhomework.entities.UserBean;

import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.query.CountQuery;
import org.greenrobot.greendao.query.DeleteQuery;
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

		MySQLiteOpenHelper devOpenHelper = new MySQLiteOpenHelper(MyApplication.mContext, DBNAME, null);

//		DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(MyApplication.mContext, DBNAME, null);
		Database encryptedWritableDb = devOpenHelper.getEncryptedWritableDb(MY_KEY);
		DaoMaster daoMaster = new DaoMaster(encryptedWritableDb);
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

		QueryBuilder<UserBean> builder = userBeanDao.queryBuilder();
		builder.where(UserBeanDao.Properties.Name.eq(userBeana.getName()));
//						.or(UserBeanDao.Properties.Name.eq("100"));
//						.and(UserBeanDao.Properties.Abc.eq("abc"))
//						.and(UserBeanDao.Properties.Weight.eq("100kg"))
//						.build();
//		build.setParameter()
//		build.list();
//		UserBean unique = builder.unique();
//		if (unique == null) {
//			return;
//		}
		List<UserBean> list = builder.list();
		for (UserBean userBean : list) {
			userBean.setName("李四");
		}
		userBeanDao.updateInTx(list);


//		String adb = unique.getName();

	}

	public void delete(UserBean userBean) {
		QueryBuilder<UserBean> queryBuilder = userBeanDao
				.queryBuilder()
				.where(
						UserBeanDao.Properties.Name.eq(userBean.getName()),
						UserBeanDao.Properties.Age.eq(userBean.getAge()),
						UserBeanDao.Properties.Weight.eq(userBean.getWeight()),
						UserBeanDao.Properties.Abc.eq(userBean.getAbc()))
				.orderAsc(UserBeanDao.Properties.Weight);

//queryBuilder.buildDelete().executeDeleteWithoutDetachingEntities();
		Query<UserBean> build = queryBuilder.build();
		List<UserBean> list = build.list();
		for (UserBean bean : list) {

			userBeanDao.delete(bean);
		}

//		DeleteQuery<UserBean> deleteQuery = userBeanDao.queryBuilder()
//				.where(UserBeanDao.Properties.Name.eq(userBean.getName()))
//				.buildDelete();

	}


	public List<UserBean> checkOne(String name, String age, String weight, String abc) {
//		"张三", "100", "100kg", "abc"
		QueryBuilder<UserBean> builder = userBeanDao
				.queryBuilder();
		QueryBuilder<UserBean> queryBuilder = builder
				.where(UserBeanDao.Properties.Name.eq(name)
//						UserBeanDao.Properties.Age.eq(age),
//						UserBeanDao.Properties.Weight.eq(weight),
//						UserBeanDao.Properties.Abc.eq(abc)
//						, builder.or(UserBeanDao.Properties.Age.eq("李四"), UserBeanDao.Properties.Age.eq("李四"))
//						, builder.and(UserBeanDao.Properties.Age.eq("李四"), UserBeanDao.Properties.Age.eq("李四"))
				).orderAsc(UserBeanDao.Properties.Weight);

		Query<UserBean> build = queryBuilder.build();

		queryBuilder.count();

		List<UserBean> list = build.list();
		return list;
	}

	public long checkCount(String name) {
		QueryBuilder<UserBean> queryBuilder = userBeanDao.queryBuilder();
		queryBuilder.where(UserBeanDao.Properties.Name.eq(name));
		CountQuery<UserBean> buildCount = queryBuilder.buildCount();
		long count = buildCount.count();
		return count;
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

	public void deleteAll() {
		userBeanDao.deleteAll();

	}
}
