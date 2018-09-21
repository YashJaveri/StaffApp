package com.imbuegen.staffapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.net.URISyntaxException;

public class RestClass{
    private String jsonString;

    protected void onCreate() {
        init();
    }

    private void init() {
    }

    private String getUsers(){

        return jsonString;
    }

    private String getPosts(){

        return jsonString;
    }

    private String getEvents(){

        return jsonString;
    }
}
