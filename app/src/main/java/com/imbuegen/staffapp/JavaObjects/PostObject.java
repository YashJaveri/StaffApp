package com.imbuegen.staffapp.JavaObjects;

public class PostObject {
    private String id;
    private String content;
    private LikesObject likeObj;
    private DisLikesObject disLikeObj;
    private String comment;

    public LikesObject getLikeObj() {
        return likeObj;
    }

    public void setLikeObj(LikesObject likeObj) {
        this.likeObj = likeObj;
    }

    public DisLikesObject getDisLikeObj() {
        return disLikeObj;
    }

    public void setDisLikeObj(DisLikesObject disLikeObj) {
        this.disLikeObj = disLikeObj;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
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
}
