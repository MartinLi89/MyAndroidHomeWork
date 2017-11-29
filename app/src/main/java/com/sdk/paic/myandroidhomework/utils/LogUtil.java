package com.sdk.paic.myandroidhomework.utils;

import android.content.Context;
import android.os.Environment;
import android.os.StatFs;
import android.text.TextUtils;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author ex-lisuyang001
 * @date 2017/11/29
 */

public class LogUtil {
	public static boolean isLogOpen = true;
	public static String LOG_TAG = "LogUtil";
	static ExecutorService mSingleTaskExecutor = Executors.newFixedThreadPool(1);
	private static SimpleDateFormat LOG_TIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
	public static SimpleDateFormat LOG_FILE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

	public static void LogInfo(String logTag, String logMsg) {
		if (isLogOpen) {
			if (logTag != null && logMsg != null) {
				Log.i(LOG_TAG, logTag + "——>" + logMsg);
				writeLogToFile(logTag, logMsg);

			}
		}
	}

	public static void LogDebug(String logTag, String logMsg) {
		if (isLogOpen) {
			Log.d(LOG_TAG, logTag + "-->" + logMsg);
			writeLogToFile(logTag, logMsg);

		}
	}

	public static void LogError(String logTag, String logMsg) {
		if (isLogOpen) {
			Log.e(LOG_TAG, logTag + "-->" + logMsg);
			writeLogToFile(logTag, logMsg);

		}
	}

	private static synchronized void writeLogToFile(final String tag, final String msg) {
		if (true) {
			return;
		}
		Date date = new Date(System.currentTimeMillis());
		final String curTime = LOG_TIME_FORMAT.format(date);
		final String curTime2 = LOG_FILE_FORMAT.format(date);
		mSingleTaskExecutor.execute(new Runnable() {
			public void run() {
				File logFile = getLogFile(curTime2);
				if (logFile != null && logFile.isFile()) {
					String logMessage = "" + curTime + " : " + tag + "  \t<||>  " + msg + "\r\n";
					printLogLine(new LogLine(logFile.getPath(), logMessage));
				}

			}
		});

	}

	/**
	 * 日志线程
	 */
	private static class LogLine {
		private String mFilePath;
		private String mPrintMessage;

		private LogLine(String filePath, String printMessage) {
			this.mFilePath = null;
			this.mPrintMessage = null;
			this.mFilePath = filePath;
			this.mPrintMessage = printMessage;
		}

		public String getFilePath() {
			return this.mFilePath;
		}

		public String getPrintMessage() {
			return this.mPrintMessage;
		}
	}

	private static final HashMap<String, Vector<LogLine>> mPrintLogMap = new HashMap();

	private static void printLogLine(LogLine log) {
		HashMap var1 = mPrintLogMap;
		synchronized (mPrintLogMap) {
			Vector LogList = (Vector) mPrintLogMap.get(log.mFilePath);
			if (LogList == null) {
				LogList = new Vector();
				mPrintLogMap.put(log.mFilePath, LogList);
			}

			LogList.add(log);
			++mCurCacheLogCount;
			if (mCurCacheLogCount > 100) {
				mSingleTaskExecutor.execute(new PrintRunnable());
			}

		}
	}

	private static class PrintRunnable implements Runnable {
		private PrintRunnable() {
		}

		public void run() {
			List printLogList = getPrintLogList();
			if (printLogList != null) {
				for (int i = 0; i < printLogList.size(); ++i) {
					this.printLog((LogLine) printLogList.get(i));
				}
			}

		}

		private void printLog(LogLine printLog) {
			FileWriter printWriter = null;
			File logFile = new File(printLog.getFilePath());

			try {
				if (logFile != null && logFile.isFile()) {
					printWriter = new FileWriter(logFile, true);
					printWriter.append(printLog.getPrintMessage());
					printWriter.flush();
				}
			} catch (FileNotFoundException var15) {
				Log.e(LOG_TAG, var15.toString());
			} catch (IOException var16) {
				Log.e(LOG_TAG, var16.toString());
			} finally {
				try {
					if (printWriter != null) {
						printWriter.close();
					}
				} catch (IOException var14) {
					Log.e(LOG_TAG, var14.toString());
				}

			}

		}
	}

	private static List<Vector<LogLine>> nextLogLine() {
		HashMap var0 = mPrintLogMap;
		synchronized (mPrintLogMap) {
			if (mPrintLogMap.size() <= 0) {
				return null;
			} else {
				ArrayList printLogList = new ArrayList();
				Iterator filePathList = mPrintLogMap.keySet().iterator();

				while (filePathList.hasNext()) {
					String filePath = (String) filePathList.next();
					Vector waitLogList = (Vector) mPrintLogMap.get(filePath);
					printLogList.add(waitLogList);
				}

				mCurCacheLogCount = 0;
				mPrintLogMap.clear();
				return printLogList;
			}
		}
	}

	private static List<LogLine> getPrintLogList() {
		List printLogArraryList = nextLogLine();
		if (printLogArraryList != null && printLogArraryList.size() > 0) {
			ArrayList printLogList = new ArrayList();

			for (int i = 0; i < printLogArraryList.size(); ++i) {
				Vector waitLogList = (Vector) printLogArraryList.get(i);
				if (waitLogList.size() > 0) {
					StringBuilder logMessage = new StringBuilder();
					String filePath = ((LogLine) waitLogList.get(0)).mFilePath;

					for (int logLine = 0; logLine < waitLogList.size(); ++logLine) {
						logMessage.append(((LogLine) waitLogList.get(logLine)).mPrintMessage);
					}

					waitLogList.clear();
					LogLine var7 = new LogLine(filePath, logMessage.toString());
					printLogList.add(var7);
				}
			}

			return printLogList;
		} else {
			return null;
		}
	}

	private static synchronized File getLogFile(String curTime) {
		File logFile = null;

		try {
			if (isSDCardEnable() && isEnoughFreeSize()) {
				String e = getLogPath();
				if (e != null && !e.trim().equals("")) {
					String tempLogFilePath = null;

					tempLogFilePath = e + File.separator + curTime;

					if (tempLogFilePath == null) {
						return null;
					}

					logFile = new File(tempLogFilePath);
					if ((logFile == null || !logFile.exists()) && !logFile.createNewFile()) {
						logFile = null;
					}

					if (logFile != null && !logFile.isFile()) {
						logFile = null;
					}
				}
			} else {
				Log.e(LOG_TAG, "SDCard 不可用 或者 SDCard 空间不足2MB");
			}
		} catch (IOException var5) {
			Log.e(LOG_TAG, var5.toString());
			logFile = null;
		}

		return logFile;
	}

	private static String getLogPath() {
		if (LOG_ABS_PATH_PRE == null) {
			LOG_ABS_PATH = getSDCardDir() + File.separator + LOG_DIR;
		} else {
			LOG_ABS_PATH = LOG_ABS_PATH_PRE + File.separator + LOG_DIR;
		}

		File logDir = new File(LOG_ABS_PATH);
		if (logDir.exists() && !logDir.isDirectory()) {
			logDir.delete();
			logDir = new File(LOG_ABS_PATH);
			if (!logDir.mkdirs()) {
				return null;
			}
		} else if (logDir != null && !logDir.exists() && logDir.mkdirs()) {
			return null;
		}

		return LOG_ABS_PATH;
	}

	/**
	 * sdcard是否为挂在状态
	 *
	 * @return
	 */
	private static boolean isSDCardEnable() {
		return Environment.getExternalStorageState().equals("mounted");
	}

	private static String LOG_ABS_PATH;
	private static String LOG_DIR = "LogDir";

	/**
	 * 内存是否足够
	 *
	 * @return
	 */
	private static boolean isEnoughFreeSize() {
		if (LOG_ABS_PATH_PRE == null) {
			LOG_ABS_PATH = getSDCardDir() + File.separator + LOG_DIR;
		} else {
			LOG_ABS_PATH = LOG_ABS_PATH_PRE + File.separator + LOG_DIR;
		}

		long nSDFreeSize = 0L;

		try {
			nSDFreeSize = getSDFreeSize(LOG_ABS_PATH);
		} catch (Exception var4) {
			return true;
		}

		long oneM = 2097152L;
		return nSDFreeSize > oneM;
	}

	static String LOG_ABS_PATH_PRE;//日志保存总路径
	static int mCurCacheLogCount = 0;

	/**
	 * 初始化日志路径
	 *
	 * @param context
	 */
	public static void initLog(Context context) {
		if (TextUtils.isEmpty(LOG_ABS_PATH_PRE)) {
			LOG_ABS_PATH_PRE = getAppStorageDir(context);
		}
		mCurCacheLogCount = 0;
		Log.i(LOG_TAG, "日志保存路径：" + LOG_ABS_PATH_PRE);
	}

	/**
	 * 获取路径
	 *
	 * @param context
	 * @return
	 */
	private static String getAppStorageDir(Context context) {
		File f = context.getExternalFilesDir((String) null);
		String storageDirectory;
		if (f == null) {
			storageDirectory = Environment.getExternalStorageDirectory().toString();
			File fDir = new File(storageDirectory);
			if (!fDir.canWrite()) {
				storageDirectory = getSDCardDir();
				if (storageDirectory != null) {
					storageDirectory = storageDirectory + File.separator + context.getApplicationInfo().packageName;
					return storageDirectory;
				} else {
					return context.getCacheDir().toString();
				}
			} else {
				storageDirectory = storageDirectory + File.separator + context.getApplicationInfo().packageName;
				return storageDirectory;
			}
		} else {
			storageDirectory = f.getAbsolutePath();
			return storageDirectory;
		}
	}

	/**
	 * 获取sdcard路径
	 *
	 * @return
	 */
	public static String getSDCardDir() {
		String pathDir = null;
		File sdfile = Environment.getExternalStorageDirectory();
		File parentFile = sdfile.getParentFile();
		File[] listFiles = parentFile.listFiles();
		if (listFiles != null) {
			long freeSizeMax = 0L;

			for (int i = 0; i < listFiles.length; ++i) {
				if (listFiles[i].canWrite()) {
					String tempPathDir = listFiles[i].getAbsolutePath();
					long tempSize = getSDFreeSize(tempPathDir);
					if (tempSize > freeSizeMax) {
						freeSizeMax = tempSize;
						pathDir = tempPathDir;
					}
				}
			}
		}

		return pathDir;
	}

	/**
	 * 获取sdcard 可用可用空间
	 *
	 * @param filePath
	 * @return
	 */
	private static long getSDFreeSize(String filePath) {
		StatFs statfs = new StatFs(filePath);
		long nBlocSize = statfs.getBlockSizeLong();
		long nAvailaBlock = statfs.getAvailableBlocksLong();
		long nSDFreeSize = nAvailaBlock * nBlocSize;
		return nSDFreeSize;
	}
}
