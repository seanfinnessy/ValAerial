package com.github.seanfinnessy.ValTracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Arrays;

@SpringBootApplication
@EnableScheduling
public class ValTrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ValTrackerApplication.class, args);
		// ConfigurableApplicationContext context = SpringApplication.run(ValTrackerApplication.class, args);
		// System.out.println("beans:" + Arrays.toString(context.getBeanDefinitionNames()));
	}

}
