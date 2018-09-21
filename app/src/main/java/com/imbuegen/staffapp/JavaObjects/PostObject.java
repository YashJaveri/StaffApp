package com.imbuegen.staffapp.JavaObjects;

import java.util.ArrayList;

public class PostObject {
    private String id;
    private String content;
    private UserObject user;

    public ArrayList<LikesObject> getLikeObj() {
        return likeObj;
    }

    public void setLikeObj(ArrayList<LikesObject> likeObj) {
        this.likeObj = likeObj;
    }

    public ArrayList<DisLikesObject> getDisLikeObj() {
        return disLikeObj;
    }

    public void setDisLikeObj(ArrayList<DisLikesObject> disLikeObj) {
        this.disLikeObj = disLikeObj;
    }

    private ArrayList<LikesObject> likeObj;
    private ArrayList<DisLikesObject> disLikeObj;
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
