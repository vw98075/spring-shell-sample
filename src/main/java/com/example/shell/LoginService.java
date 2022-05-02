package com.example.shell;

import org.springframework.stereotype.Service;

@Service
public class LoginService {

    String token;

    String username; //TODO: to be removed later

    public void login(String username, String password){
        this.token ="";// TODO
        this.username = username; // TODO
    }

    void logout(){
        token = null;
        this.username = null;
    }
    boolean isLoggedIn(){
        return token != null; // TODO: and the token is not expired
    }

    String loggedInUser(){
        return username; // TODO: current logged in user's username
    }
}

