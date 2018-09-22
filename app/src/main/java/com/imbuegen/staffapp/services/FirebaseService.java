package com.imbuegen.staffapp.services;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
<<<<<<< HEAD
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.ResponseHandlerInterface;
=======
import com.imbuegen.staffapp.Constants;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
>>>>>>> 15324a010784668bc0b638205a69c35d8ef67b5e

import java.io.Console;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import cz.msebera.android.httpclient.Header;

public class FirebaseService extends FirebaseInstanceIdService {
    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d("FCMSERVICE", "Refreshed token: " + refreshedToken);
<<<<<<< HEAD

    }
    public  void sendTokenToServer(final String token) {
        AsyncHttpClient client = new AsyncHttpClient();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String hash = preferences.getString("HashCode",null);
        client.addHeader("Authorization",hash);
        RequestParams params = new RequestParams();
        params.put("fcm_token", token);
        client.post("http://tsec-18.herokuapp.com", params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                Log.d("FCM","token-sent");
=======
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String UserHash = preferences.getString("HashCode",null);
        AsyncHttpClient client = new AsyncHttpClient();
        client.addHeader("Authentication","token " + UserHash);
        client.addHeader("fcm_token",refreshedToken);
        client.post(Constants.BASE_URL + "/user/fcm", new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

>>>>>>> 15324a010784668bc0b638205a69c35d8ef67b5e
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }
}
