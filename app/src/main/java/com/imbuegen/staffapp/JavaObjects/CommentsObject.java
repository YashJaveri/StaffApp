package com.imbuegen.staffapp.JavaObjects;

public class CommentsObject {

    private UserObject user;
    private String comment;

    public CommentsObject(UserObject user, String comment) {
        this.user = user;
        this.comment = comment;
    }

    public UserObject getUser() {
        return user;
    }

    public String getComment() {
        return comment;
    }
}
