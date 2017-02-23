package com.example.tylerperdue.concertapplication;

/**
 * Created by tylerperdue on 2/9/17.
 */

public class User {
    // Labels table name
    public static final String TABLE = "User";

    // Labels Table Columns names
    public static final String KEY_ID = "id";
    public static final String KEY_username = "username";
    public static final String KEY_password = "password";

    // Properties to help us keep data
    private int id;
    private String username;
    private String password;

    // Getters and Setters
    public int getId() {
        return id;
    }
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    public void setId(int id){
        this.id = id;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
