package com.Bubba;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class Simplelearn8Application {



	public static void main(String[] args) {
		SpringApplication.run(Simplelearn8Application.class, args);
	}
	@Autowired
	private Environment environment;

}
