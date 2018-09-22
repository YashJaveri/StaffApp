package com.imbuegen.staffapp.JavaObjects;

import java.util.ArrayList;
import java.util.Calendar;

public class UserObject {
    private int employeeID;
    private String name;
    private String email;
    private String DOB;
    private String status;
    private String annivDATE;
    private String joiningDate;
    private ArrayList<String> familyEmails;
    private int points;
    private String department;
    private String position;
    private ArrayList<PostObject> postObjects;
    private ArrayList<EventObject> eventObjects;

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public ArrayList<PostObject> getPostObjects() {
        return postObjects;
    }

    public void setPostObjects(ArrayList<PostObject> postObjects) {
        this.postObjects = postObjects;
    }

    public ArrayList<EventObject> getEventObjects() {
        return eventObjects;
    }

    public void setEventObjects(ArrayList<EventObject> eventObjects) {
        this.eventObjects = eventObjects;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public  String getAnnivDATE() {

        return annivDATE;
    }

    public void setAnnivDATE(String annivDATE) {
        this.annivDATE = annivDATE;
    }

    public String getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(String joiningDate) {
        this.joiningDate = joiningDate;
    }

    public ArrayList<String> getFamilyEmails() {
        return familyEmails;
    }

    public void setFamilyEmails(ArrayList<String> familyEmails) {
        this.familyEmails = familyEmails;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getposition() {
        return position;
    }

    public void setposition(String position) {
        this.position = position;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
