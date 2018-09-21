package com.imbuegen.staffapp.JavaObjects;

import java.util.ArrayList;

public class PostObject {
    private String id;
    private String content;
    private UserObject user;

    public ArrayList<LikesObject> getlikeObjss() {
        return likeObjs;
    }

    public void setlikeObjs(ArrayList<LikesObject> likeObjs) {
        this.likeObjs = likeObjs;
    }

    public ArrayList<DisLikesObject> getDislikeObjs() {
        return dislikeObjs;
    }

    public void setDislikeObjs(ArrayList<DisLikesObject> dislikeObjs) {
        this.dislikeObjs = dislikeObjs;
    }

    private ArrayList<LikesObject> likeObjs;
    private ArrayList<DisLikesObject> dislikeObjs;
    private ArrayList<CommentsObject> comments;

    public ArrayList<CommentsObject> getComment() {
        return comments;
    }

    public void setComment(ArrayList<CommentsObject> comment) {
        this.comments = comment;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public UserObject getUser() {
        return user;
    }

    public void setUser(UserObject user) {
        this.user = user;
    }
}
