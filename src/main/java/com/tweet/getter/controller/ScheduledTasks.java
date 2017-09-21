package com.tweet.getter.controller;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseCredentials;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.tweet.getter.model.Tweet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.List;

/**
 * Created by frknikiz on 30.08.2016.
 */
@Component
public class ScheduledTasks {

    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    private DatabaseReference ref;

    @Autowired
    private GetTweets tweet;

    @Value("${firebase.url}")
    private String fireBaseUrl;

    @Value("${tweet.hastag}")
    private String hastag;

    @Value("${firebase.serviceAccountJsonFileName}")
    private String serviceAccountJsonFileName;

    @PostConstruct
    private void initialize() throws IOException {

        Resource resource = new ClassPathResource(serviceAccountJsonFileName);

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredential(FirebaseCredentials.fromCertificate(resource.getURL().openStream()))
                .setDatabaseUrl(fireBaseUrl)
                .build();
        FirebaseApp.initializeApp(options);
        FirebaseDatabase defaultDatabase = FirebaseDatabase.getInstance();
        ref = defaultDatabase.getReference("tweets");
    }

    @Scheduled(fixedRate = 60000)
    public void reportCurrentTime() throws IOException {

        List<Tweet> result = tweet.searchTweet(hastag).getResult();

        if (result.size() != 0) {
            ref.setValue(result);
            ref.push();
            log.info(result.size() + " Tweet gönderildi.");
        } else {
            log.info("0 Sonuç Döndü.");
        }


    }
}
