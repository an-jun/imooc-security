package com.wen.security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.wen.security.core.properties.SecurityProperties;

@SpringBootApplication
public class App {

	
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
		System.out.println("hello world");

	//	System.out.println(HttpWenService.htmlFiter(HttpWenService.httpRequest("")));
	}
}
