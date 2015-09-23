package com.weaverprojects.instagramapi.lib.server.Model;

/**
 * Created by Keith on 2015-09-22.
 */
public class Res_UserInfo {
    private int id;
    private String username;
    private String full_name;

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }
}
