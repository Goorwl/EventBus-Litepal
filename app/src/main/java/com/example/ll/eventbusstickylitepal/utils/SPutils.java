package com.example.ll.eventbusstickylitepal.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Description : sp工具类
 * Copyright   : (c) 2016
 * Author      : Goorwl
 * Email       : goorwl@163.com
 * Date        : 2016/12/22 16:48
 */

public class SPutils {

    public static boolean getboolean(Context context, String key, boolean defValue) {
        SharedPreferences sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        boolean getBoolean = sp.getBoolean(key, defValue);
        return getBoolean;
    }

    public static void putboolean(Context context, String key, boolean value) {
        SharedPreferences sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    public static int getInt(Context context, String key, int defValue) {
        SharedPreferences sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        int getInt = sp.getInt(key, defValue);
        return getInt;
    }

    public static void putInt(Context context, String key, int value) {
        SharedPreferences sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    public static String getString(Context context, String key, String defValue) {
        SharedPreferences sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        String getString = sp.getString(key, defValue);
        return getString;
    }

    public static void putString(Context context, String key, String value) {
        SharedPreferences sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public static void remove(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.remove(key);
        editor.commit();
    }
}
