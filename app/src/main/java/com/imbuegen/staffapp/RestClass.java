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
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class RestClass {
    //Other
    StringBuilder sb;
    private Context context;
    private MyJsonClass myJsonClass;
    //SF:
    private SharedPreferences mSharedPref;
    //Web data:
    private String jsonString;
    private String params;
    private String hashCode;

    public interface RestListner{
        void onComplete(String json,String code);
    }

    private  RestListner listner;

    public RestClass(Context context,RestListner listner) {
        this.context = context;
        this.listner = listner;
    }

    public void onCreate() {
        init();
    }

    private void init() {
        mSharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        myJsonClass = new MyJsonClass();

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

    public void getPosts(int id,String code) {
        params = "/post/list/" + Integer.toString(id);
        String[] asyncparams = {Constants.BASE_URL + params,code};
        myJsonClass.execute(asyncparams);
    }

    public void getUser(String code){
        params = "/user/";
        String[] asyncparams = {Constants.BASE_URL + params,code};
        myJsonClass.execute(asyncparams);
    }

    public void updatePost(int id,String code) {
        params = "/post/" + Integer.toString(id) + "/update";
        String[] asyncparams = {Constants.BASE_URL + params,code};
        myJsonClass.execute(asyncparams);
    }

    public void deletePost(int id,String code) {
        params = "/post/" + Integer.toString(id) + "/delete";
        String[] asyncparams = {Constants.BASE_URL + params,code};
        myJsonClass.execute(asyncparams);
    }

    public void likePost(String _id, String code){
        params = "/post/" + _id + "/like";
        String[] asyncparams = {Constants.BASE_URL + params,code};
        myJsonClass.execute(asyncparams);
    }

    public void dislikePost(String _id, String code){
        params = "/post/" + _id + "/dislike";
        String[] asyncparams = {Constants.BASE_URL + params,code};
        myJsonClass.execute(asyncparams);
    }

    public void undoLike(String _id, String code){
        params = "/post/" + _id + "/undolike";
        String[] asyncparams = {Constants.BASE_URL + params,code};
        myJsonClass.execute(asyncparams);
    }

    public void undoDislike(String _id, String code){
        params = "/post/" + _id + "/undodislike";
        String[] asyncparams = {Constants.BASE_URL + params,code};
        myJsonClass.execute(asyncparams);
    }

    public void newPost(int id, String code){
        params = "/post/" + Integer.toString(id) + "/new";
        String[] asyncparams = {Constants.BASE_URL + params,code};
        myJsonClass.execute(asyncparams);
    }

    @SuppressLint("StaticFieldLeak")
    private class MyJsonClass extends AsyncTask<String, Integer, ArrayList<String>> {
        private ProgressDialog pd;

        @Override
        protected void onPreExecute() {
            pd = ProgressDialog.show(context, "Fetching Data", "Please wait!", false);
        }


        @Override
        protected ArrayList<String> doInBackground(String... strings) {
            String jsonString;
            try {
                URL url = new URL(strings[0]);
                Log.d("StaffApp",url.toString());
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setRequestProperty("token", Constants.TOKEN);
                connection.connect();
                Log.d("StaffApp", "Connected");
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                String line;    //temp to read lines
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                    Log.d("StaffApp", line);
                }
                Log.d("StaffApp", "Read");
                Log.d("StaffApp",sb.toString());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                jsonString = sb.toString();
            }
            ArrayList<String> list = new ArrayList<>();
            list.add(jsonString);
            list.add(strings[1]);
            return list;
        }

        @Override
        protected void onPostExecute(ArrayList<String> array) {
            listner.onComplete(array.get(0),array.get(1));
        }
    }
}