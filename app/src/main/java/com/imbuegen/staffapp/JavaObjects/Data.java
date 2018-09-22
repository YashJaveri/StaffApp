package com.imbuegen.staffapp.JavaObjects;

import android.content.Context;
import android.util.Log;

import com.imbuegen.staffapp.RestClass;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Data {
    Context context;
    RestClass rc;
    public  Data (Context context){
        this.context = context;
    }
    public  void init(){
        rc = new RestClass(context);
    }
    public  void getPosts(int page, final RestClass.RestListner<JSONArray> listner){
        rc.getPosts(page, new RestClass.RestListner<String>() {
            @Override
            public void onComplete(String jsonString) {
                try {
                    Log.d("ONCP",jsonString);
                    JSONArray bject = new JSONObject(jsonString).getJSONArray("docs");
                    listner.onComplete(bject);
                }catch (Exception e){

                }
            }
        });
    }
    public void getCurrentUser(final RestClass.RestListner<JSONObject> listner){
        rc.getUser(new RestClass.RestListner<String>() {
            @Override
            public void onComplete(String jsonString) {
                try {
                    listner.onComplete( new JSONObject(jsonString));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    public void getEventObjects(int page, final RestClass.RestListner<JSONObject> listner){
        rc.getEvents(page, new RestClass.RestListner<String>() {
            @Override
            public void onComplete(String jsonString) {
                try {
                    listner.onComplete(new JSONObject(jsonString));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    public void updateEvent(String _id, String message){
        rc.updateEvents(_id, message);
    }

    public void deleteEvent(String _id){
        rc.deleteEvent(_id);
    }
    public void like(String _id){
        rc.likePost(_id);
    }
    public void dislike(String _id){
        rc.dislikePost(_id);
    }
    public void undoLike(String _id){
        rc.undoLike(_id);
    }
    public void undoDislike(String _id){
        rc.undoDislike(_id);
    }
}
