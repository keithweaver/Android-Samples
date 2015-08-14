package com.weaverprojects.listview;

/**
 * Created by Weaver on 15-05-08.
 */
public class User {
    String name;
    String user;

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
    public void setName(String n){
        this.name = n;
    }
    public void setUser(String n){
        this.user = n;
    }
}
