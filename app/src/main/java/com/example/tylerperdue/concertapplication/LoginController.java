package com.example.tylerperdue.concertapplication;

/**
 * Created by tylerperdue on 2/8/17.
 */

public class LoginController {

    private boolean authenticated;
    private String email;
    private String password;

    public LoginController(String email, String password){
        this.email = email;
        this.password = password;
    }

    public boolean authenticate(){
        authenticated = email.equals("test@example.com") && password.equals("password");
        return authenticated;
    }
}
