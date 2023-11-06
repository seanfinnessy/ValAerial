package com.github.seanfinnessy.ValTracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ValTrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ValTrackerApplication.class, args);
	}

}
