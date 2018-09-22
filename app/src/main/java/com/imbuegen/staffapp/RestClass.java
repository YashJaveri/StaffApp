package com.imbuegen.staffapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.imbuegen.staffapp.JavaObjects.EventObject;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;

public class RestClass {
    private Context context;
    //SF:
    private SharedPreferences mSharedPref;
    //Web data:
    private String params;
    private String hashCode;

    public RestClass(Context context) {
        this.context = context;
    }

    public void onCreate() {
        init();
    }


    public  interface RestListner<T>{
        void onComplete(T jsonString);


    }

    private void init() {
        mSharedPref = PreferenceManager.getDefaultSharedPreferences(context);

        if (getHashCode())
            Log.d("StaffApp", "Got HashCode: " + hashCode);
        else
            Log.d("StaffApp", "Not got");
    }

    public void getUser(final RestListner RestListner) {
        params = "/user/";
        AsyncHttpClient client = new AsyncHttpClient();
        client.addHeader("Authorization", Constants.TOKEN);
        client.get(Constants.BASE_URL + params, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {

            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                RestListner.onComplete(responseString);
            }
        });
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



    public void getEvents(int _id, final RestListner RestListner) {
        params = "/event/list" + String.valueOf(_id);

        AsyncHttpClient client = new AsyncHttpClient();
        client.addHeader("Authorization", Constants.TOKEN);
        client.get(Constants.BASE_URL + params, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Log.d("StaffApp", String.valueOf(statusCode));
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                Log.d("StaffApp", responseString);
                RestListner.onComplete(responseString);
            }
        });
    }

    public void updateEvents(String _id, String message) {
        params = "/event/" + _id + "/update";
        Log.d("MyAPP", params);
        AsyncHttpClient client = new AsyncHttpClient();
        client.addHeader("Authorization", Constants.TOKEN);
        client.addHeader("content", message);
        client.post(Constants.BASE_URL + params, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {

            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                Log.d("StaffApp", "I Succeded");
            }
        });
    }

    public void deleteEvent(String _id) {
        params = "/event/" + _id + "/delete";
        AsyncHttpClient client = new AsyncHttpClient();
        client.addHeader("Authorization", Constants.TOKEN);
        client.post(Constants.BASE_URL + params, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {

            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {

            }
        });
    }

    public void getPosts(int id, final RestListner RestListner) {
        params = "/post/list/" + String.valueOf(id);
        Log.d("StaffApp", "GetPost Post get req");
        AsyncHttpClient client = new AsyncHttpClient();
        client.addHeader("Authorization", Constants.TOKEN);
        client.get(Constants.BASE_URL + params, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Log.d("StaffApp", String.valueOf(statusCode));
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                Log.d("StaffApp", responseString);
                RestListner.onComplete(responseString);
            }
        });
    }

    public void deletePost(String id) {
        params = "/post/" + id + "/delete";
        AsyncHttpClient client = new AsyncHttpClient();
        client.addHeader("Authorization", Constants.TOKEN);
        client.post(Constants.BASE_URL + params, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {

            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {

            }
        });
    }

    public void updatePost(String _id, String content) {
        params = "/post/" + _id + "/update";
        Log.d("MyAPP", params);
        AsyncHttpClient client = new AsyncHttpClient();
        client.addHeader("Authorization", Constants.TOKEN);
        client.addHeader("content", content);
        client.post(Constants.BASE_URL + params, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {

            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                Log.d("StaffApp", "I Succeded");
            }
        });
    }

    public void likePost(String _id) {
        params = "/post/" + _id + "/like";
        AsyncHttpClient client = new AsyncHttpClient();
        client.addHeader("Authorization", Constants.TOKEN);
        client.post(Constants.BASE_URL + params, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {

            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {

            }
        });
    }

    public void dislikePost(String _id) {
        params = "/post/" + _id + "/dislike";
        AsyncHttpClient client = new AsyncHttpClient();
        client.addHeader("Authorization", Constants.TOKEN);
        client.post(Constants.BASE_URL + params, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {

            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {

            }
        });
    }

    public void undoLike(String _id) {
        params = "/post/" + _id + "/undolike";
        AsyncHttpClient client = new AsyncHttpClient();
        client.addHeader("Authorization", Constants.TOKEN);
        client.post(Constants.BASE_URL + params, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {

            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {

            }
        });
    }

    public void undoDislike(String _id) {
        params = "/post/" + _id + "/undodislike";
        AsyncHttpClient client = new AsyncHttpClient();
        client.addHeader("Authorization", Constants.TOKEN);
        client.post(Constants.BASE_URL + params, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {

            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {

            }
        });
    }

    public void newPost(String content) {
        params = "/post/new";
        AsyncHttpClient client = new AsyncHttpClient();
        client.addHeader("Authorization", Constants.TOKEN);
        client.addHeader("content", content);
        client.post(Constants.BASE_URL + params, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {

            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {

            }
        });
    }

}