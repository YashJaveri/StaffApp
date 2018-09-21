package com.imbuegen.staffapp.Controllers;

//import com.google.gson.Gson;
import com.imbuegen.staffapp.JavaObjects.CommentsObject;
import com.imbuegen.staffapp.JavaObjects.DisLikesObject;
import com.imbuegen.staffapp.JavaObjects.PostObject;
import com.imbuegen.staffapp.JavaObjects.LikesObject;
import com.imbuegen.staffapp.JavaObjects.UserObject;

import java.util.ArrayList;

public class DataController {
//    private Gson mGson;
    //Objects
    private ArrayList<UserObject> users;
    private ArrayList<PostObject> posts;
    private ArrayList<LikesObject> likesObjs;
    private ArrayList<DisLikesObject> disLikesObjs;
    private ArrayList<CommentsObject> commentsObjs;
    //Web
  //  private RestClass restClass;

    public ArrayList<UserObject> requestUsers() {

        return users;
    }

    public ArrayList<PostObject> requestPosts() {
        return posts;
    }

    public ArrayList<LikesObject> requestLikesObjs() {
        return likesObjs;
    }

    public ArrayList<DisLikesObject> requestDisLikesObjs() {
        return disLikesObjs;
    }

    public ArrayList<CommentsObject> requestCommentsObjs() {
        return commentsObjs;
    }
}
