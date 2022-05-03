package com.example.shell;

import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public record LoginCommands(LoginService service) {

    @ShellMethod("login")
    public void login(String username, String password){
        service.login(username, password);
    }

    @ShellMethod("logout")
    public void logout(){
        service.logout();
    }

    public Availability logoutAvailability(){
        return this.service.isLoggedIn() ?
                Availability.available() :
                Availability.unavailable("Not sign in yet");
    }
}
