package com.weaverprojects.test5;

import android.app.Activity;

/**
 * Created by Weaver on 15-05-24.
 */
public class User extends Activity {
    String user;
    String name;

    public User(String name, String user){
        super();
        this.name = name;
        this.user = user;
    }
    public String getName(){
        return name;
    }
    public String getUser(){
        return user;
    }
    public void setUser(String s){
        this.name = s;
    }
    public void setName(String s){
        this.name = s;
    }
}
