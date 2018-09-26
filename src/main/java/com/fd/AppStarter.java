package com.fd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class AppStarter {

	/**
	 * mvn deploy
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(AppStarter.class, args);
	}
}
