package com.workintech.twitterclone.mapper;

import com.workintech.twitterclone.dto.retweet.RetweetRequestDto;
import com.workintech.twitterclone.dto.retweet.RetweetResponseDto;
import com.workintech.twitterclone.entity.Retweet;
import com.workintech.twitterclone.entity.Tweet;
import com.workintech.twitterclone.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RetweetMapper {
    private final TweetMapper tweetMapper;

    public RetweetResponseDto toResponse(Retweet retweet) {
        return new RetweetResponseDto(retweet.getContent(), tweetMapper.toResponse(retweet.getTweet()));
    }

}
