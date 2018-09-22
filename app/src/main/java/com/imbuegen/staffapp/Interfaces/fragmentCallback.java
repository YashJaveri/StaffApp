package com.imbuegen.staffapp.Interfaces;

import com.imbuegen.staffapp.JavaObjects.UserObject;

import org.json.JSONArray;
import org.json.JSONObject;

public interface fragmentCallback {
    void showComments(JSONArray comments);

    void showUser(JSONObject user);
}
