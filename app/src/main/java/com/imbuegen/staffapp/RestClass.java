package com.imbuegen.staffapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.util.Log;

import com.google.gson.Gson;

public class RestClass {
    private Context context;
    //SF:
    private SharedPreferences mSharedPref;
    private Gson mGson;
    private SharedPreferences.Editor editor;
    //Web data:
    private String jsonString;
    private String params;
    private String hashCode;

    public RestClass(Context context) {
        this.context = context;
    }

    protected void onCreate() {
        init();
    }

    private void init() {
        PreferenceManager.getDefaultSharedPreferences(context);

        mGson = new Gson();
        editor = mSharedPref.edit();
        if (getHashCode())
            Log.d("StaffApp", "Got HashCode: " + hashCode);
        else
            Log.d("StaffApp", "Not got");
    }

    protected void setHashCode(String _hashCode) {
        if (mSharedPref.getString("HashCode", "") != null)
            hashCode = mSharedPref.getString("HashCode", "");
        else {
            this.hashCode = _hashCode;
            editor.putString("HashCode", _hashCode);
            editor.apply();
        }
    }

    private Boolean getHashCode() {
        if (mSharedPref.getString("HashCode", "") != null) {
            hashCode = mSharedPref.getString("HashCode", "");
            return true;
        } else
            return false;
    }

    private String getUsers() {

        return jsonString;
    }

    private String getPosts() {

        return jsonString;
    }

    private String getEvents() {

        return jsonString;
    }

    private AsyncTask<String, >{

    }
}
