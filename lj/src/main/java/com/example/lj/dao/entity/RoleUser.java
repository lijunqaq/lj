package com.example.lj.dao.entity;

public class RoleUser {
    private String userID;

    private String roleID;

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID == null ? null : userID.trim();
    }

    public String getRoleID() {
        return roleID;
    }

    public void setRoleID(String roleID) {
        this.roleID = roleID == null ? null : roleID.trim();
    }
}