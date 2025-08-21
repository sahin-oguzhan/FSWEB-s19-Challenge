package com.workintech.twitterclone.dto.retweet;

import com.workintech.twitterclone.dto.tweet.TweetResponseDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record RetweetResponseDto(String content, TweetResponseDto tweetResponseDto) {
}
