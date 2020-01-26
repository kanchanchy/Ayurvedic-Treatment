package com.applicationslab.ayurvedictreatment.utility;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by Kanchan on 6/19/2016.
 */
public class PreferenceUtil {

    Context mContext;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor spEditor;

    private final String USER_NAME = "user_name";
    private final String EMAIL = "email";
    private final String PASSWORD = "password";

    public PreferenceUtil(Context mContext)
    {
        super();
        this.mContext = mContext;
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
    }

    public void clearPreference() {
        spEditor = sharedPreferences.edit();
        spEditor.clear();
        spEditor.commit();
    }

    public void setUserName (String userName) {
        spEditor = sharedPreferences.edit();
        spEditor.putString(USER_NAME, userName);
        spEditor.commit();
    }

    public String getUserName () {
        return sharedPreferences.getString(USER_NAME, "");
    }

    public void setEmail (String email) {
        spEditor = sharedPreferences.edit();
        spEditor.putString(EMAIL, email);
        spEditor.commit();
    }

    public String getEmail () {
        return sharedPreferences.getString(EMAIL, "");
    }

    public void setPassword (String password) {
        spEditor = sharedPreferences.edit();
        spEditor.putString(PASSWORD, password);
        spEditor.commit();
    }

    public String getPassword () {
        return sharedPreferences.getString(PASSWORD, "");
    }

}
