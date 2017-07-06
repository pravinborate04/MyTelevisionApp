package com.example.webwerks1.myapp;

/**
 * Created by webwerks1 on 17/5/16.
 */
public class User {

    private String name;
    private String password;
    private String session_id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSession_id() {
        return session_id;
    }

    public void setSession_id(String session_id) {
        this.session_id = session_id;
    }
}
