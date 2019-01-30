package com.yaer.remittance.utils;


import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Base64;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;

/**
 * sharepreference 存储工具类
 */
public class SharedPreferencesUtils {
    /**
     * 保存在手机里面的名字
     */
    public static final String FILE_NAME = "shared_data";

    /**
     * 保存数据的方法，拿到数据保存数据的基本类型，然后根据类型调用不同的保存方法
     *
     * @param key
     * @param object
     */
    public static void saveData(Context context, String key, Object object) {
        if(key == null || object == null){
            return;
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String type = object.getClass().getSimpleName();
        if ("Integer".equals(type)) {
            editor.putInt(key, (Integer) object);
        } else if ("Boolean".equals(type)) {
            editor.putBoolean(key, (Boolean) object);
        } else if ("String".equals(type)) {
            editor.putString(key, (String) object);
        } else if ("Float".equals(type)) {
            editor.putFloat(key, (Float) object);
        } else if ("Long".equals(type)) {
            editor.putLong(key, (Long) object);
        } else {
            editor.putString(key, object.toString());
        }
        editor.commit();
    }

    /**
     * 从文件中读取数据
     *
     * @param key
     * @param defaultObject
     */
    public static Object getData(Context context, String key, Object defaultObject) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        //defValue为为默认值，如果当前获取不到数据就返回它
        String type = defaultObject.getClass().getSimpleName();
        if ("Integer".equals(type)) {
            return sharedPreferences.getInt(key, (Integer) defaultObject);
        } else if ("Boolean".equals(type)) {
            return sharedPreferences.getBoolean(key, (Boolean) defaultObject);
        } else if ("String".equals(type)) {
            return sharedPreferences.getString(key, (String) defaultObject);
        } else if ("Float".equals(type)) {
            return sharedPreferences.getFloat(key, (Float) defaultObject);
        } else if ("Long".equals(type)) {
            return sharedPreferences.getLong(key, (Long) defaultObject);
        } else {
            return sharedPreferences.getString(key, null);
        }

    }

    /**
     * writeObject 方法负责写入特定类的对象的状态，以便相应的 readObject 方法可以还原它
     * 最后，用Base64.encode将字节文件转换成Base64编码保存在String中
     *
     * @param object 待加密的转换为String的对象
     * @return String   加密后的String
     */
    private static String Object2String(Object object) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = null;
        try {
            objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(object);
            String string = new String(Base64.encode(byteArrayOutputStream.toByteArray(), Base64.DEFAULT));
            objectOutputStream.close();
            return string;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 使用Base64解密String，返回Object对象
     *
     * @param objectString 待解密的String
     * @return object      解密后的object
     */
    private static Object String2Object(String objectString) {
        byte[] mobileBytes = Base64.decode(objectString.getBytes(), Base64.DEFAULT);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(mobileBytes);
        ObjectInputStream objectInputStream = null;
        try {
            objectInputStream = new ObjectInputStream(byteArrayInputStream);
            Object object = objectInputStream.readObject();
            objectInputStream.close();
            return object;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * 移除某个key值已经对应的值
     *
     * @param key
     */
    public static void remove(Context context, String key) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(key).commit();
    }

    /**
     * 清除所有的数据
     */
    public static void clear(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear().commit();
    }

    /**
     * 查询某个key是否存在
     *
     * @param key
     * @return
     */
    public static boolean contains(Context context, String key) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.contains(key);
    }
}

