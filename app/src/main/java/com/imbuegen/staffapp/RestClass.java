package com.imbuegen.staffapp;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class RestClass {
    //Other
    StringBuilder sb;
    private Context context;
    private GetTypeAsyncTask getTypeAsyncTask;
    private PostTypeAsyncTask postTypeAsyncTask;
    //SF:
    private SharedPreferences mSharedPref;
    //Web data:
    private String jsonString;
    private String params;
    private String hashCode;

    public interface RestListner {
        void onComplete(String json, String code);
    }

    private RestListner listner;

    public RestClass(Context context, RestListner listner) {
        this.context = context;
        this.listner = listner;
    }

    public void onCreate() {
        init();
    }

    private void init() {
        mSharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        getTypeAsyncTask = new GetTypeAsyncTask();

        if (getHashCode())
            Log.d("StaffApp", "Got HashCode: " + hashCode);
        else
            Log.d("StaffApp", "Not got");
    }

    protected void setHashCode(String _hashCode) {
        if (mSharedPref.getString("HashCode", null) != null)
            hashCode = mSharedPref.getString("HashCode", "");
        else {
            SharedPreferences.Editor editor = mSharedPref.edit();
            this.hashCode = _hashCode;
            editor.putString("HashCode", _hashCode);
            editor.apply();
        }
    }

    private Boolean getHashCode() {
        if (mSharedPref.getString("HashCode", null) != null) {
            hashCode = mSharedPref.getString("HashCode", "");
            return true;
        } else
            return false;
    }

    public void getPosts(int id, String code) {
        params = "/post/list/";
        String[] asyncparams = {Constants.BASE_URL + params, code};
        getTypeAsyncTask.execute(asyncparams);
        Log.d("StaffApp", "GetPost Post get req");
    }

    public void getUser(String code) {
        params = "/user/";
        String[] asyncparams = {Constants.BASE_URL + params, code};
        getTypeAsyncTask.execute(asyncparams);
    }

    public void deletePost(String id, String code) {
        params = "/post/" + id + "/delete";
        String[] asyncparams = {Constants.BASE_URL + params, code};
        Log.d("StaffApp", "Deleteing Post post req");
        getTypeAsyncTask.execute(asyncparams);
    }
/*
    public void updatePost(int id, String code) {
        params = "/post/" + Integer.toString(id) + "/update";
        String[] asyncparams = {Constants.BASE_URL + params, code};
        postTypeAsyncTask.execute(asyncparams);
    }

    public void likePost(String _id, String code) {
        params = "/post/" + _id + "/like";
        String[] asyncparams = {Constants.BASE_URL + params, code};
        getTypeAsyncTask.execute(asyncparams);
    }

    public void dislikePost(String _id, String code) {
        params = "/post/" + _id + "/dislike";
        String[] asyncparams = {Constants.BASE_URL + params, code};
        getTypeAsyncTask.execute(asyncparams);
    }

    public void undoLike(String _id, String code) {
        params = "/post/" + _id + "/undolike";
        String[] asyncparams = {Constants.BASE_URL + params, code};
        getTypeAsyncTask.execute(asyncparams);
    }

    public void undoDislike(String _id, String code) {
        params = "/post/" + _id + "/undodislike";
        String[] asyncparams = {Constants.BASE_URL + params, code};
        getTypeAsyncTask.execute(asyncparams);
    }

    public void newPost(int id, String code) {
        params = "/post/" + Integer.toString(id) + "/new";
        String[] asyncparams = {Constants.BASE_URL + params, code};
        getTypeAsyncTask.execute(asyncparams);
    }*/
}