package com.yizhu.lj.dao.entity;

public class RoleModule {
    private String roleID;

    private String moduleID;

    public String getRoleID() {
        return roleID;
    }

    public void setRoleID(String roleID) {
        this.roleID = roleID == null ? null : roleID.trim();
    }

    public String getModuleID() {
        return moduleID;
    }

    public void setModuleID(String moduleID) {
        this.moduleID = moduleID == null ? null : moduleID.trim();
    }
}