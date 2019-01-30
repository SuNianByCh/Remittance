package com.yaer.remittance.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;

import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by ymy on 2018/3/23.
 */

public class CommonUtils {
    private static final String STR_MD5 = "MD5";
    private static final String STR_EMPTY = "";
    /**
     * 2 * 获取版本号 3 * @return 当前应用的版本号 4
     */
    public static String getVersion(Context context) {
        String ver = "2";
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo info = packageManager.getPackageInfo(
                    context.getPackageName(), 0);
            if (info.versionCode > 0) {
                ver = String.valueOf(info.versionCode);
            }
        } catch (Exception e) {
        }
        return ver;
    }

    public static String getVersionName(Context context) {
        String ver = "2.0";
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo info = packageManager.getPackageInfo(
                    context.getPackageName(), 0);
            if (!StringUtils.isEmpty(info.versionName)) {
                ver = String.valueOf(info.versionName);
            }
        } catch (Exception e) {
        }
        return ver;
    }
    public static String getMD5(String content) {
        if (content != null && !"".equals(content)) {
            try {
                MessageDigest digest = MessageDigest.getInstance(STR_MD5);
                digest.update(content.getBytes());
                return getHashString(digest);

            } catch (NoSuchAlgorithmException e) {
                Log.e("yyt", e.getMessage());
            }
            return STR_EMPTY;
        }
        return "";
    }
    private static String getHashString(MessageDigest digest) {
        StringBuilder builder = new StringBuilder(STR_EMPTY);
        for (byte b : digest.digest()) {
            builder.append(Integer.toHexString((b >> 4) & 0xf));
            builder.append(Integer.toHexString(b & 0xf));
        }
        return builder.toString();
    }



    /*public static String getDeviceInfo(Context context) {
        try {
            org.json.JSONObject json = new org.json.JSONObject();
            android.telephony.TelephonyManager tm = (android.telephony.TelephonyManager) context
                    .getSystemService(Context.TELEPHONY_SERVICE);
            String device_id = null;
            if (checkPermission(context, Manifest.permission.READ_PHONE_STATE)) {
                device_id = tm.getDeviceId();
            }
            String mac = getMac(context);

            json.put("mac", mac);
            if (StringUtils.isEmpty(device_id)) {
                device_id = mac;
            }
            if (StringUtils.isEmpty(device_id)) {
                device_id = android.provider.Settings.Secure.getString(context.getContentResolver(),
                        android.provider.Settings.Secure.ANDROID_ID);
            }
            json.put("device_id", device_id);
            return json.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }*/

    /*public static String getMac(Context context) {
        String mac = "";
        if (context == null) {
            return mac;
        }
        if (Build.VERSION.SDK_INT < 23) {
            mac = getMacBySystemInterface(context);
        } else {
            mac = getMacByJavaAPI();
            if (StringUtils.isEmpty(mac)){
                mac = getMacBySystemInterface(context);
            }
        }
        return mac;

    }*/

    /*@TargetApi(9)
    private static String getMacByJavaAPI() {
        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            while (interfaces.hasMoreElements()) {
                NetworkInterface netInterface = interfaces.nextElement();
                if ("wlan0".equals(netInterface.getName()) || "eth0".equals(netInterface.getName())) {
                    byte[] addr = netInterface.getHardwareAddress();
                    if (addr == null || addr.length == 0) {
                        return null;
                    }
                    StringBuilder buf = new StringBuilder();
                    for (byte b : addr) {
                        buf.append(String.format("%02X:", b));
                    }
                    if (buf.length() > 0) {
                        buf.deleteCharAt(buf.length() - 1);
                    }
                    return buf.toString().toLowerCase(Locale.getDefault());
                }
            }
        } catch (Throwable e) {
        }
        return null;
    }*/

    /*private static String getMacBySystemInterface(Context context) {
        if (context == null) {
            return "";
        }
        try {
            WifiManager wifi = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
            if (checkPermission(context, Manifest.permission.ACCESS_WIFI_STATE)) {
                WifiInfo info = wifi.getConnectionInfo();
                return info.getMacAddress();
            } else {
                return "";
            }
        } catch (Throwable e) {
            return "";
        }
    }*/

    public static boolean checkPermission(Context context, String permission) {
        boolean result = false;
        if (context == null) {
            return result;
        }
        if (Build.VERSION.SDK_INT >= 23) {
            try {
                Class<?> clazz = Class.forName("android.content.Context");
                Method method = clazz.getMethod("checkSelfPermission", String.class);
                int rest = (Integer) method.invoke(context, permission);
                if (rest == PackageManager.PERMISSION_GRANTED) {
                    result = true;
                } else {
                    result = false;
                }
            } catch (Throwable e) {
                result = false;
            }
        } else {
            PackageManager pm = context.getPackageManager();
            if (pm.checkPermission(permission, context.getPackageName()) == PackageManager.PERMISSION_GRANTED) {
                result = true;
            }
        }
        return result;
    }
    public static String GetManifestMarge(Context context,String marge){
        try {
            ApplicationInfo appInfo = context.getPackageManager()
                    .getApplicationInfo(context.getPackageName(),
                            PackageManager.GET_META_DATA);
            String margeManifest = appInfo.metaData.getString(marge);
            Log.e("margemarge=","==" + margeManifest);
            return margeManifest ;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "" ;
        }
    }

}
