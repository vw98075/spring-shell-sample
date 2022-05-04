package com.example.shell;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.stereotype.Service;

import org.springframework.http.MediaType;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.util.List;

@Service
public class LoginService {

    final Logger log = LoggerFactory.getLogger(LoginService.class);

    @Value("${service.url}")
    protected String apiBaseurl;

    protected final OkHttpClient client = new OkHttpClient();

    protected final Moshi moshi = new Moshi.Builder().build();

    ParameterizedType userListType = Types.newParameterizedType(List.class, SecurityProperties.User.class);
    JsonAdapter<List<SecurityProperties.User>> userListJsonAdapter = moshi.adapter(userListType);

    String token;

    String username; //TODO: to be removed later

    private final JsonAdapter<IdToken> idTokenJsonAdapter = moshi.adapter(IdToken.record);

    public boolean login(String username, String password){

//        http://localhost:8080/api/authenticate
        Request request = new Request.Builder()
                .url(apiBaseurl + "/authenticate")
                .header("User-Agent", "OkHttp Headers.java")
                .addHeader("Accept", MediaType.APPLICATION_JSON.toString())
                .put(new LoginData(username, password, true))
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                log.error(response.code() + " - " + response.message());
                return false;
            }
            IdToken watchInfo = idTokenJsonAdapter.fromJson(response.body().string());
            this.token = watchInfo.idtoken;
            this.username = username;
            log.debug(watchInfo.toString());
            return true;
        } catch (IOException ioe) {
            log.error(ioe.getMessage());
            return false;
        }
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

record LoginData(String username, String password, boolean rememberMe){
}

record IdToken(String idToken){}