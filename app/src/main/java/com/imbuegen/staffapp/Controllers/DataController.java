package com.imbuegen.staffapp.Controllers;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.imbuegen.staffapp.Constants;
import com.imbuegen.staffapp.JavaObjects.CommentsObject;
import com.imbuegen.staffapp.JavaObjects.DisLikesObject;
import com.imbuegen.staffapp.JavaObjects.PostObject;
import com.imbuegen.staffapp.JavaObjects.LikesObject;
import com.imbuegen.staffapp.JavaObjects.UserObject;
import com.imbuegen.staffapp.RestClass;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DataController{
    //Other
    private Context context;
    private Gson mGson;
    private String jsonString;
    private RestClass rc;
    //Objects
    private ArrayList<UserObject> users;
    private ArrayList<PostObject> posts;
    private ArrayList<LikesObject> likesObjs;
    private ArrayList<DisLikesObject> disLikesObjs;
    private ArrayList<CommentsObject> commentsObjs;
    //Web
    private RestClass restClass;

    public DataController(Context context) {
        this.context = context;
    }

    public UserObject requestCurrentUser() throws JSONException {
        restClassGetter();
        rc.onCreate();
        rc.getUser(Constants.CURRENT_USER);
        JSONObject mainJSonObject = new JSONObject(jsonString);

        return  jsonToUser(mainJSonObject);
    }

    public ArrayList<PostObject> requestPosts(int id) {
        restClassGetter();

        rc.onCreate();
        rc.getPosts(id, Constants.POST_LIST);
        JSONArray mainJsonArray = null;
        try {
            mainJsonArray = new JSONArray(jsonString);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (mainJsonArray != null) {
            ArrayList<PostObject> postObjects = new ArrayList<>();
            for (int i=0; i < mainJsonArray.length(); i++){
                JSONObject jsonObject = null;
                PostObject postObject = new PostObject();
                try {
                    assert jsonObject != null;
                    postObject.set_id(jsonObject.getString("_id"));
                    postObject.setContent(jsonObject.getString("content"));
                    postObject.setlikeObjs(jsonToLikeObj(jsonObject.getJSONArray("likes")));
                    postObject.setDislikeObjs(jsonToDislikeObj(jsonObject.getJSONArray("dislikes")));
                    postObject.setComment(jsonToCommentObjs(jsonObject.getJSONArray("comments")));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                postObjects.add(postObject);
            }
            return postObjects;
        }
        return posts;
    }
 /*   public ArrayList<LikesObject> requestLikesObjs(String _id) throws JSONException {
        restClassGetter();

        rc.likePost(_id, Constants.POST_LIST);
        JSONArray mainJsonArray = null;
        mainJsonArray = new JSONArray(jsonString);
        return jsonToLikeObj(mainJsonArray);
    }

    public ArrayList<DisLikesObject> requestDisLikesObjs(int _id) {
        return disLikesObjs;
    }

    public ArrayList<CommentsObject> requestCommentsObjs(int _id) {
        return commentsObjs;
    }*/
    private ArrayList<DisLikesObject> jsonToDislikeObj(JSONArray jsonArray){
        ArrayList<DisLikesObject> _dislikeObjects = new ArrayList<>();
        for (int i=0; i<jsonArray.length(); i++){
            try {
                DisLikesObject disLikesObj = new DisLikesObject(jsonArray.getJSONObject(i).getInt("employId"));
                _dislikeObjects.add(disLikesObj);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return _dislikeObjects;
    }

    private ArrayList<LikesObject> jsonToLikeObj(JSONArray jsonArray){
        ArrayList<LikesObject> _likeObjects = new ArrayList<>();
        for (int i=0; i<jsonArray.length(); i++){
            try {
                LikesObject likesObject = new LikesObject(jsonArray.getJSONObject(i).getInt("employId"));
                _likeObjects.add(likesObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return _likeObjects;
    }

    private ArrayList<CommentsObject> jsonToCommentObjs(JSONArray jsonArray){
        ArrayList<CommentsObject> _commentObjs = new ArrayList<>();
        for (int i=0; i<jsonArray.length(); i++){
            try {
                CommentsObject commentsObject = new CommentsObject(jsonToUser(jsonArray.getJSONObject(i).getJSONObject("user")),jsonArray.getJSONObject(i).getString("message"));
                _commentObjs.add(commentsObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return _commentObjs;
    }

    private UserObject jsonToUser(JSONObject _jsonObject) throws JSONException {
        UserObject userObject = new UserObject();

        userObject.setEmployeeID(_jsonObject.getInt("employId"));
        userObject.setName("");
        userObject.setEmail(_jsonObject.getString("email"));
        userObject.setAnnivDATE(_jsonObject.getString("aniversaryDate"));
        userObject.setDOB(_jsonObject.getString("DOB"));
        userObject.setDepartment(_jsonObject.getString("department"));
        ArrayList<String> familyemailsArray = new ArrayList<>();
        for(int i=0; i<_jsonObject.getString("familyEmails").length(); i++)
            familyemailsArray.add(_jsonObject.getJSONArray("familyEmails").get(i).toString());
        userObject.setFamilyEmails(familyemailsArray);
        userObject.setJoiningDate(_jsonObject.getString("joiningDate"));
        userObject.setStatus(_jsonObject.getString("status"));
        userObject.setPoints(_jsonObject.getInt("points"));
        userObject.setposition(_jsonObject.getString("position"));

        return userObject;
    }

    private void restClassGetter() {
        rc = new RestClass(context, new RestClass.RestListner() {
            @Override
            public void onComplete(String json, String code) {
                jsonString = json;
            }
        });
    }
}
