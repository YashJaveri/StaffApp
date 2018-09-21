package com.imbuegen.staffapp.services;

import android.content.Context;
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

        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d("FCMSERVICE", "Refreshed token: " + refreshedToken);
        sendTokenToServerAsync asn = new sendTokenToServerAsync();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String UserHash = preferences.getString("HashCode",null);
        asn.execute(refreshedToken,UserHash);
    }
    class sendTokenToServerAsync extends  AsyncTask<String,Void,Void>{

        @Override
        protected Void doInBackground(String... strings) {
            try {
                URL url = new URL("http://tsec-18.herokuapp.com/user/fcm/" + strings[0]);

                HttpURLConnection connection = (HttpURLConnection)url.openConnection();

                if(strings[1] != null){
                    connection.setRequestMethod("POST");
                    connection.addRequestProperty("Authorization","token " + "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6IjViYTRmZDUyY2VhNmU3NWM4MWFmYzVjYyJ9.6J52iC4770C0fl8BnIvYMXgradbAsRapyw_d-DJT71w");
                    Log.d("vsdv",strings[0]);
                }
                connection.connect();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
