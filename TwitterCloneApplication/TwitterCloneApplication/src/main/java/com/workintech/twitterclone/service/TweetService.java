package com.workintech.twitterclone.service;

import com.workintech.twitterclone.dto.tweet.TweetRequestDto;
import com.workintech.twitterclone.dto.tweet.TweetResponseDto;
import com.workintech.twitterclone.entity.Tweet;

import java.util.List;

public interface TweetService{
    public List<TweetResponseDto> getAll();
    public List<TweetResponseDto> findByUserName(String username);
    public TweetResponseDto create(TweetRequestDto tweetRequestDto);
    public TweetResponseDto update(Long id, TweetRequestDto tweetRequestDto);
    public void delete(Long id);

}
