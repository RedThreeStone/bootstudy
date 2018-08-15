package it.lei.day1.entity;

import java.util.List;

public class User {
    private Integer ssmUserId;

    private Integer userAge;

    private String username;

    private String password;

    private String userSex;

    List<Permission> permissions;

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

    public Integer getSsmUserId() {
        return ssmUserId;
    }

    public void setSsmUserId(Integer ssmUserId) {
        this.ssmUserId = ssmUserId;
    }

    public Integer getUserAge() {
        return userAge;
    }

    public void setUserAge(Integer userAge) {
        this.userAge = userAge;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex == null ? null : userSex.trim();
    }
}