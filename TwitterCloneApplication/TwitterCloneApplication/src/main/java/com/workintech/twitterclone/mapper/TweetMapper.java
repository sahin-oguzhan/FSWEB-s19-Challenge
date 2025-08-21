package com.workintech.twitterclone.mapper;

import com.workintech.twitterclone.dto.tweet.TweetRequestDto;
import com.workintech.twitterclone.dto.tweet.TweetResponseDto;
import com.workintech.twitterclone.entity.Tweet;
import org.springframework.stereotype.Component;

@Component
public class TweetMapper {

    public TweetResponseDto toResponse(Tweet tweet){

        return new TweetResponseDto(tweet.getContent(),
                tweet.getUser(),
                tweet.getLikes(),
                tweet.getComments(),
                tweet.getRetweets());
    }

    public Tweet toEntity(TweetRequestDto tweetRequestDto){
        Tweet tweet = new Tweet();

        if (tweetRequestDto.content() != null)
            tweet.setContent(tweetRequestDto.content());

        return tweet;
    }
}
