package com.example.andrewoh.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/* PreferencesUtil Class
 * 파일 형태의 저장소 (Key, Value 형태)
 * 사용목적 : 외부클래스의 데이터를 이용하기 위해서
 */
@SuppressWarnings("static-access")
public class PreferencesUtil {

    //String형 변수를 특정 키값을 만들고 그 안에 저장을한다.
    public static void setPreferences(Context context, String key, String value) {
        SharedPreferences p = context.getSharedPreferences("pref", context.MODE_PRIVATE);
        SharedPreferences.Editor editor = p.edit();
        editor.putString(key, value);
        editor.commit();
    }

    //키값을 입력하여 그에 해당하는 키값안에 있는 값을 불러오게된다.
    public static String getPreferences(Context context, String key) {
        SharedPreferences p = context.getSharedPreferences("pref", context.MODE_PRIVATE);
        p = context.getSharedPreferences("pref", context.MODE_PRIVATE);
        return p.getString(key, "");
    }

    //Int형 변수를 특정 키값을 만들고 그 안에 저장을한다.
    public static void setIntPreferences(Context context, String key, int value) {
        SharedPreferences p = context.getSharedPreferences("pref", context.MODE_PRIVATE);
        SharedPreferences.Editor editor = p.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    //키값을 입력하여 그에 해당하는 키값안에 있는 값을 불러오게된다.
    public static int getIntPreferences(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences("pref", Activity.MODE_PRIVATE);
        int myIntValue = sp.getInt(key, -1);
        return myIntValue;
    }
}
