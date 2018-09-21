package com.imbuegen.staffapp.services;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class FirebaseService extends FirebaseInstanceIdService {
    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d("FCMSERVICE", "Refreshed token: " + refreshedToken);
        sendTokenToServer(refreshedToken);
    }
    public  void sendTokenToServer(final String token){
        Handler handler = new Handler();
        try {
            handler.post(new Runnable() {

                    URL url = new URL("http://tsec-18.herokuapp.com/user/token/" + token);

                @Override
                public void run() {
                    try {
                        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
                        String UserHash = preferences.getString("HashCode",null);
                        if(UserHash != null){
                            connection.setRequestMethod("POST");
                            connection.addRequestProperty("token",UserHash);

                        }
                        connection.connect();


                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
