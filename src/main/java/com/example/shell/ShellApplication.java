package com.example.shell;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.shell.standard.ShellComponentonent;
//import org.springframework.shell.standard.ShellMethod;

@SpringBootApplication
public class ShellApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShellApplication.class, args);
	}

}

//@ShellComponent
//		class MyCommand {
//
//	@ShellMethod("Add two integers together.")
//	public int add(int a, int b) {
//		return a + b;
//	}
//}