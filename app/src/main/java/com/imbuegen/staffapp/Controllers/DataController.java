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
import com.imbuegen.staffapp.JavaObjects.EventObject;
import com.imbuegen.staffapp.JavaObjects.PostObject;
import com.imbuegen.staffapp.JavaObjects.LikesObject;
import com.imbuegen.staffapp.JavaObjects.UserObject;
import com.imbuegen.staffapp.RestClass;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Console;
import java.util.ArrayList;

public class DataController {
    //Other
    private Context context;
    private Gson mGson;
    private String jsonString;
    private RestClass rc;

    private static final String TAG = "DataController";

    //Objects
    private ArrayList<UserObject> users;
    private ArrayList<PostObject> posts;
    private ArrayList<LikesObject> likesObjs;
    private ArrayList<DisLikesObject> disLikesObjs;
    private ArrayList<CommentsObject> commentsObjs;
    //Web
    //  private RestClass restClass;

    public DataController(Context context) {
        this.context = context;
    }

    public void onCreate() {
        rc = new RestClass(context);
        rc.onCreate();
    }

    public ArrayList<EventObject> requestEvents(String _id) {
        ArrayList<EventObject> eventObjects = new ArrayList<>();

        rc.getEvents(_id, new RestClass.RestListner() {
            @Override
            public void onComplete(String _jsonString) {
                jsonString = _jsonString;
            }
        });
        JSONObject mainJsonObj = null;
        try {
            mainJsonObj = new JSONObject(jsonString);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            assert mainJsonObj != null;
            if (mainJsonObj.getJSONArray("docs") != null) {
                ArrayList<PostObject> postObjects = new ArrayList<>();
                for (int i = 0; i < mainJsonObj.getJSONArray("docs").length(); i++) {
                    JSONObject jsonObject = null;
                    EventObject eventObject = new EventObject();
                    try {
                        assert jsonObject != null;
                        eventObject.set_id(jsonObject.getString("_id"));
                        eventObject.setMessage(jsonObject.getString("messages"));
                        eventObject.setEventDate(jsonObject.getString("eventDate"));
                        eventObject.setPostDate(jsonObject.getString("postDate"));
                        eventObject.setUser(jsonToUser(jsonObject.getJSONObject("user")));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    eventObjects.add(eventObject);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return eventObjects;
    }

    public void updateEvent(String _id, String message){
        rc.updateEvents(_id, message);
    }

    public void deleteEvent(String _id){
        rc.deleteEvent(_id);
    }
    public UserObject requestCurrentUser() throws JSONException {

        rc.getUser(new RestClass.RestListner() {
            @Override
            public void onComplete(String _jsonString) {
                jsonString = _jsonString;
            }
        });
        JSONObject mainJSonObject = new JSONObject(jsonString);

        return jsonToUser(mainJSonObject);
    }

    public void deletePost(String id) {
        rc.deletePost(id);
    }

    public void updatePost(String _id, String content) {
        Log.d("MYAPP", _id);
        rc.updatePost(_id, content);
    }

    public void newPost(String content) {
        rc.newPost(content);
    }

    public ArrayList<PostObject> requestPosts(int id) {


        rc.getPosts(id, new RestClass.RestListner() {
            @Override
            public void onComplete(String _jsonString) {
                jsonString = _jsonString;
            }
        });

        //Log.d("StaffApp", "JsonObj" + jsonString);

        JSONObject mainJsonObj = null;
        try {
            mainJsonObj = new JSONObject(jsonString);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            assert mainJsonObj != null;
            if (mainJsonObj.getJSONArray("docs") != null) {
                ArrayList<PostObject> postObjects = new ArrayList<>();
                for (int i = 0; i < mainJsonObj.getJSONArray("docs").length(); i++) {
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
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return posts;
    }

    public void like(String _id) {
        rc.likePost(_id);
    }

    public void dislike(String _id) {
        rc.dislikePost(_id);
    }

    public void undoLike(String _id) {
        rc.undoLike(_id);
    }

    public void undoDislike(String _id) {
        rc.undoDislike(_id);
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
    private ArrayList<DisLikesObject> jsonToDislikeObj(JSONArray jsonArray) {
        ArrayList<DisLikesObject> _dislikeObjects = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                DisLikesObject disLikesObj = new DisLikesObject(jsonArray.getJSONObject(i).getInt("employId"));
                _dislikeObjects.add(disLikesObj);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return _dislikeObjects;
    }

    private ArrayList<LikesObject> jsonToLikeObj(JSONArray jsonArray) {
        ArrayList<LikesObject> _likeObjects = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                LikesObject likesObject = new LikesObject(jsonArray.getJSONObject(i).getInt("employId"));
                _likeObjects.add(likesObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return _likeObjects;
    }

    private ArrayList<CommentsObject> jsonToCommentObjs(JSONArray jsonArray) {
        ArrayList<CommentsObject> _commentObjs = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                CommentsObject commentsObject = new CommentsObject(jsonToUser(jsonArray.getJSONObject(i).getJSONObject("user")), jsonArray.getJSONObject(i).getString("message"));
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
        for (int i = 0; i < _jsonObject.getString("familyEmails").length(); i++)
            familyemailsArray.add(_jsonObject.getJSONArray("familyEmails").get(i).toString());
        userObject.setFamilyEmails(familyemailsArray);
        userObject.setJoiningDate(_jsonObject.getString("joiningDate"));
        userObject.setStatus(_jsonObject.getString("status"));
        userObject.setPoints(_jsonObject.getInt("points"));
        userObject.setposition(_jsonObject.getString("position"));
        return userObject;
    }
}