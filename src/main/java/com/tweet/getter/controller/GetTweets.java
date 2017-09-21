package com.tweet.getter.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tweet.getter.manager.TweetManager;
import com.tweet.getter.manager.TwitterCriteria;
import com.tweet.getter.model.Tweet;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by frknikiz on 30.08.2016.
 */
@Component
public class GetTweets {


    private ObjectMapper mapper;

    private TwitterCriteria criteria;

    private List<Tweet> result;

    private Tweet t;

    @Value("${tweet.limit}")
    private int limit;

    public GetTweets() {
        mapper = new ObjectMapper();

    }


    public GetTweets searchTweet(String key) {

        criteria = TwitterCriteria.create()
                .setQuerySearch(key).setMaxTweets(limit);

        this.result = TweetManager.getTweets(criteria);

        return this;
    }

    public String getJsonResult() {
        try {
            return mapper.writeValueAsString(this.result);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "[]";

    }

    public List<Tweet> getResult() {
        return result;
    }

}
