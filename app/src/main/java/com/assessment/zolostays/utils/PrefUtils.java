package com.assessment.zolostays.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.assessment.zolostays.db.model.User;

/**
 * Created by DELL on 22-07-2017.
 */

public class PrefUtils {

    public static final String ISLOGIN = "isLogin";

    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String EMAIL = "email";
    public static final String PHONE = "phone";
    public static final String PASSWORD = "password";

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

    public void save(User user){
        editor.putLong(ID, user.getUser_id())
                .putString(NAME, user.getName())
                .putString(EMAIL, user.getEmail())
                .putString(PHONE, user.getPhone())
                .putString(PASSWORD, user.getPassword())
                .apply();
    }

    public void remove(){
        editor.clear().apply();
    }

    public User getCurrentUser(){
        User user = new User();
        user.setUser_id(preferences.getLong(ID, 0));
        user.setName(preferences.getString(NAME, null));
        user.setEmail(preferences.getString(EMAIL, null));
        user.setPhone(preferences.getString(PHONE, null));
        user.setPassword(preferences.getString(PASSWORD, null));
        if (user.getUser_id() != 0 ){
            return user;
        }
        return null;
    }

    public void updateUser(User user){
        save(user);
    }
}
