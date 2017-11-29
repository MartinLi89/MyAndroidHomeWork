package com.sdk.paic.myandroidhomework.utils;

import android.app.Activity;
import android.content.Context;
import android.telephony.TelephonyManager;

import java.util.UUID;

/**
 * @author ex-lisuyang001
 * @date 2017/11/29
 */

public class CommonUtils {

	/**
	 * 获取设备uuid
	 *
	 * @param context
	 * @return
	 */
	public static String getMyUUID(Activity context) {
		final TelephonyManager tm = (TelephonyManager) context.getBaseContext()
				.getSystemService(Context.TELEPHONY_SERVICE);

		final String tmDevice, tmSerial, tmPhone, androidId;
		tmDevice = "" + tm.getDeviceId();
		tmSerial = "" + tm.getSimSerialNumber();
		androidId = "" + android.provider.Settings
				.Secure.getString(context.getContentResolver(),
						android.provider.Settings.Secure.ANDROID_ID);

		UUID deviceUuid = new UUID(androidId.hashCode(),
				((long) tmDevice.hashCode() << 32) | tmSerial.hashCode());

		String uniqueId = deviceUuid.toString();
//		CRMLog.LogError("uuid", "uuid=" + uniqueId);
		return uniqueId;
	}

}
