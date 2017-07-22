package com.assessment.zolostays.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by DELL on 22-07-2017.
 */

public class PrefUtils {

    public static final String ISLOGIN = "isLogin";
    private boolean islogin = false;

    public SharedPreferences preferences;
    public SharedPreferences.Editor editor;

    public Context context;

    public PrefUtils(Context context) {
        this.context = context;
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
        editor = preferences.edit();
    }

    public void setIslogin(boolean islogin) {
        this.islogin = islogin;
        editor.putBoolean(ISLOGIN, islogin).apply();
    }

    public boolean isLogin() {
        return preferences.getBoolean(ISLOGIN, false);
    }
}
