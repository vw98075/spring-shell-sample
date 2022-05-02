package com.example.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class DateTimeFinder {

    @ShellMethod("Find date & time for giving time zone")
    public String findDateTime(String zone){
        return "Date & time for " + zone;
    }
}
