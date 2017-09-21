package com.tweet.getter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class TweetGetterApplication {

	public static void main(String[] args) {
		SpringApplication.run(TweetGetterApplication.class, args);
	}
}
